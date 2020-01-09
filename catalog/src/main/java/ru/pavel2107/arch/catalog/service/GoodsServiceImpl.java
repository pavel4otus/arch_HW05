package ru.pavel2107.arch.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.catalog.Utils;
import ru.pavel2107.arch.catalog.domain.*;
import ru.pavel2107.arch.catalog.dto.GoodDto;
import ru.pavel2107.arch.catalog.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodMskRepo mskRepository;
    @Autowired
    private GoodSpbRepo spbRepository;
    @Autowired
    private GoodProvincialRepo othRepository;

    /*@Autowired
    public GoodsServiceImpl( GoodMskRepo mskRepository, GoodSpbRepo spbRepository, GoodProvincialRepo othRepository){
        this.mskRepository = mskRepository;
        this.spbRepository = spbRepository;
        this.othRepository = othRepository;
    }*/

    @Override
    public GoodDto find(User user, Long id) {
        GoodDto dto;
        if (hasLocation(user)) {
            GoodRepo repository = getNearestRepository(user);
            Good good = (Good) repository.findById(id).orElse(new Good());
            dto = Utils.toDto(good);
        } else {
            Optional<GoodMsk> goodMsk = mskRepository.findById(id);
            Optional<GoodSpb> goodSpb = spbRepository.findById(id);
            Optional<GoodProvincial> goodProvincial = othRepository.findById(id);
            if (goodMsk.isPresent()) {
                dto = toDto(goodMsk.get());
            } else {
                if (goodSpb.isPresent()) {
                    dto = toDto(goodSpb.get());
                } else {
                    dto = toDto(goodProvincial.get());
                }
            }
        }

        return dto;
    }


    @Override
    public List<GoodDto> findByName(User user, String name) {
        List<GoodDto> dtos;
        if (hasLocation(user)) {
            GoodRepo repository = getNearestRepository(user);
            List<Good> goods = repository.findByName(name);
            dtos = Utils.toDtoList(goods);
        } else {
            List<GoodMsk> goodMsks = mskRepository.findByName(name);
            List<GoodSpb> goodSpbs = spbRepository.findByName(name);
            List<GoodProvincial> goodProvincials = othRepository.findByName(name);
            dtos = concatLists(goodMsks, goodSpbs, goodProvincials);
        }

        return dtos;
    }

    @Override
    public List<GoodDto> findByCategory(User user, Long categoryId) {
        List<GoodDto> dtos;
        if (hasLocation(user)) {
            GoodRepo repository = getNearestRepository(user);
            List<Good> goods = repository.findByCategory(categoryId);
            dtos = Utils.toDtoList(goods);
        } else {
            List<GoodMsk> goodMsks = mskRepository.findByCategory(categoryId);
            List<GoodSpb> goodSpbs = spbRepository.findByCategory(categoryId);
            List<GoodProvincial> goodProvincials = othRepository.findByCategory(categoryId);
            dtos = concatLists(goodMsks, goodSpbs, goodProvincials);
        }

        return dtos;
    }

    @Override
    public List<GoodDto> findByBrand(User user, Long brandId) {
        List<GoodDto> dtos;
        if (hasLocation(user)) {
            GoodRepo repository = getNearestRepository(user);
            List<Good> goods = repository.findByBrand(brandId);
            dtos = Utils.toDtoList(goods);
        } else {
            List<GoodMsk> goodMsks = mskRepository.findByBrand(brandId);
            List<GoodSpb> goodSpbs = spbRepository.findByBrand(brandId);
            List<GoodProvincial> goodProvincials = othRepository.findByBrand(brandId);
            dtos = concatLists(goodMsks, goodSpbs, goodProvincials);
        }

        return dtos;
    }

    @Override
    public List<GoodDto> findAll(User user) {
        List<GoodDto> dtos;
        if (hasLocation(user)) {
            GoodRepo repository = getNearestRepository(user);
            List<Good> goods = repository.findAll();
            dtos = Utils.toDtoList(goods);
        } else {
            List<GoodMsk> goodMsks = mskRepository.findAll();
            List<GoodSpb> goodSpbs = spbRepository.findAll();
            List<GoodProvincial> goodProvincials = othRepository.findAll();
            dtos = concatLists(goodMsks, goodSpbs, goodProvincials);
        }

        return dtos;
    }

    private List<GoodDto> concatLists(List<GoodMsk> listMosdow, List<GoodSpb> listSpb, List<GoodProvincial> listOthers) {
        List<GoodDto> list1 =
                listSpb.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());

        List<GoodDto> list2 = listMosdow.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        List<GoodDto> list3 = listOthers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return Stream.concat(Stream.concat(list1.stream(), list2.stream()), list3.stream()).collect(Collectors.toList());

    }

    private GoodDto toDto(GoodMsk x) {
        return new GoodDto(x.getId(), x.getSkud(), x.getName(), x.getDescription(),
                x.getCategory(), x.getBrand(), x.getWareHouses());
    }

    private GoodDto toDto(GoodSpb x) {
        if (x == null) return null;
        return new GoodDto(x.getId(), x.getSkud(), x.getName(), x.getDescription(),
                x.getCategory(), x.getBrand(), x.getWareHouses());
    }

    private GoodDto toDto(GoodProvincial x) {
        return new GoodDto(x.getId(), x.getSkud(), x.getName(), x.getDescription(),
                x.getCategory(), x.getBrand(), x.getWareHouses());
    }

    //
    // функция шардирования в зависимости от города
    // идет разделение
    //
    private GoodRepo getNearestRepository(User user) {
        GoodRepo repository = null;
        String location = getNearestCity(user);
        if (location.equals("SPB")) {
            repository = spbRepository;
        } else {
            if (location.equals("MSK")) {
                repository = mskRepository;
            } else {
                repository = othRepository;
            }
        }
        return repository;
    }


    private boolean hasLocation(User user) {
        return user != null && !"".equals(user.getAddress());
    }

    private String getNearestCity(User user) {
        String result = "others";
        if (user.getAddress().indexOf("Москва", 0) > 0) {
            result = "MSK";
        }
        if (user.getAddress().indexOf("Санкт-Петербург", 0) > 0) {
            result = "SPB";
        }
        System.out.println("Для пользователя " + user.getEmail() + " выбран критерий поиска " + result);
        return result;
    }
}
