package cz.tul.service;


import cz.tul.data.Locality;
import cz.tul.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LocalityService {

    @Autowired
    private LocalityRepository localityRepository;

    public void create(Locality locality) {
        localityRepository.save(locality);
    }

    public boolean exists(String username) {
        return localityRepository.exists(username);
    }

    public List<Locality> getAllLocalities() {
        return StreamSupport.stream(localityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteLocalities() {
        localityRepository.deleteAll();
    }
}
