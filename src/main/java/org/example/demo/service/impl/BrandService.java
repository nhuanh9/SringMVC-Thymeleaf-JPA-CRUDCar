package org.example.demo.service.impl;

import org.example.demo.model.Brand;
import org.example.demo.repository.BrandRepository;
import org.example.demo.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Autowired
    BrandRepository brandRepository;
    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void remove(Long id) {
        brandRepository.deleteById(id);
    }

}
