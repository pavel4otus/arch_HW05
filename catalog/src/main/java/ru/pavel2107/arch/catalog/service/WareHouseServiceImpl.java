package ru.pavel2107.arch.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.catalog.domain.WareHouse;
import ru.pavel2107.arch.catalog.repository.StorageRepo;

import java.util.List;

@Service
public class WareHouseServiceImpl implements WareHouseService {

    private StorageRepo repository;


    @Autowired
    public WareHouseServiceImpl(StorageRepo repository) {
        this.repository = repository;
    }

    @Override
    public WareHouse save(WareHouse wareHouse) {
        return repository.save(wareHouse);
    }

    @Override
    public void delete(Long id) {
        WareHouse wareHouse = find(id);
        if (wareHouse != null) {
            repository.delete(wareHouse);
        }
    }

    @Override
    public WareHouse find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<WareHouse> findAll() {
        return repository.findAll();
    }
}
