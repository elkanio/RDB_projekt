package cz.tul.service;

import cz.tul.data.Trace;
import cz.tul.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TraceService {

    @Autowired
    private TraceRepository traceRepository;

    public void create(Trace trace) {
        traceRepository.save(trace);
    }

    public boolean exists(String username) {
        return traceRepository.exists(username);
    }

    public List<Trace> getAllTracies() {
        return StreamSupport.stream(traceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteTracies() {
        traceRepository.deleteAll();
    }
}
