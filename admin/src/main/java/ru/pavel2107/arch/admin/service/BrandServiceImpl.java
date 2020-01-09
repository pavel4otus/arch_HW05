package ru.pavel2107.arch.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.admin.domain.catalog.Brand;
import ru.pavel2107.arch.admin.repository.catalog.BrandRepo;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepo repository;

    @Autowired
    public BrandServiceImpl(BrandRepo repository) {
        this.repository = repository;
    }


    @Override
    public Brand save(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public void delete(Long id) {
        Brand brand = find(id);
        if (brand != null) {
            repository.delete(brand);
        }
    }

    @Override
    public List<Brand> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Brand find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Brand findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }
}