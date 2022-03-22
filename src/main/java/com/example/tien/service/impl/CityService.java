package com.example.tien.service.impl;

import com.example.tien.model.City;
import com.example.tien.repository.CityRepository;
import com.example.tien.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public Page<City> findAllByName(String name, Pageable pageable) {
        return cityRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public City findCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
