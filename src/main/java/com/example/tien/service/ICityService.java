package com.example.tien.service;

import com.example.tien.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICityService {
    Page<City> findAll(Pageable pageable);

    Page<City> findAllByName(String name,Pageable pageable);

    City findCityById(Long id);

    void save(City city);

    void delete(Long id);
}
