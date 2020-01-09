package ru.pavel2107.arch.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.catalog.domain.Brand;
import ru.pavel2107.arch.catalog.repository.BrandRepo;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepo repository;

    @Autowired
    public BrandServiceImpl(BrandRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return repository.findById(id).orElse(new Brand());
    }
}
