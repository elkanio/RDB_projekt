package cz.tul.service;

import cz.tul.data.BetweenStop;
import cz.tul.repositories.BetweenStopRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BetweenStopService {

    @Autowired
    private BetweenStopRepository betweenStopRepository;

    public void create(BetweenStop betweenStop) {
        betweenStopRepository.save(betweenStop);
    }


    public List<BetweenStop> getAllBetweenStops() {
        return StreamSupport.stream(betweenStopRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteBetweenStops() {
        betweenStopRepository.deleteAll();
    }
}
