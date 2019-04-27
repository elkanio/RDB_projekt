package cz.tul.service;

import cz.tul.data.Bus;
import cz.tul.repositories.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BusService {

    @Autowired
    private BusRepository busRepository;

    public void create(Bus bus) {
        busRepository.save(bus);
    }

    public boolean exists(String username) {
        return busRepository.exists(username);
    }

    public List<Bus> getAllBusies() {
        return StreamSupport.stream(busRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteBusies() {
        busRepository.deleteAll();
    }
}
