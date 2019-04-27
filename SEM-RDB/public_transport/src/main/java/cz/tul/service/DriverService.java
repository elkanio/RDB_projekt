package cz.tul.service;

import cz.tul.data.Driver;
import cz.tul.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public void create(Driver driver) {
        driverRepository.save(driver);
    }

    public boolean exists(String username) {
        return driverRepository.exists(username);
    }

    public List<Driver> getAllDrivers() {
        return StreamSupport.stream(driverRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteDrivers() {
        driverRepository.deleteAll();
    }
}
