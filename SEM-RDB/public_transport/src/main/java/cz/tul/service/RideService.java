package cz.tul.service;

import cz.tul.data.Ride;
import cz.tul.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public void create(Ride ride) {
        rideRepository.save(ride);
    }


    public List<Ride> getAllRides() {
        return StreamSupport.stream(rideRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteRides() {
        rideRepository.deleteAll();
    }
}
