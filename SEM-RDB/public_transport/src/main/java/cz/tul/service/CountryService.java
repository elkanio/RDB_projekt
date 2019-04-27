package cz.tul.service;

import cz.tul.data.Country;
import cz.tul.repositories.CountryRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void create(Country country) {
        countryRepository.save(country);
    }

    public boolean exists(String username) {
        return countryRepository.exists(username);
    }

    public List<Country> getAllCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteCountries() {
        countryRepository.deleteAll();
    }
}
