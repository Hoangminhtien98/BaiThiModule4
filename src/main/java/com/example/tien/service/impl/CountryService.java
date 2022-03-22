package com.example.tien.service.impl;

import com.example.tien.model.Country;
import com.example.tien.repository.CountryRepository;
import com.example.tien.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }
}
