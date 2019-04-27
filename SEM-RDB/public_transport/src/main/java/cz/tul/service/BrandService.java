package cz.tul.service;

import cz.tul.data.Brand;
import cz.tul.data.Country;
import cz.tul.repositories.BrandRepository;
import cz.tul.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public void create(Brand brand) {
        brandRepository.save(brand);
    }

    public boolean exists(String username) {
        return brandRepository.exists(username);
    }

    public List<Brand> getAllBrands() {
        return StreamSupport.stream(brandRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteBrands() {
        brandRepository.deleteAll();
    }
}
