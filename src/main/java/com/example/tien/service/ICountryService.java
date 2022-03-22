package com.example.tien.service;

import com.example.tien.model.Country;

public interface ICountryService {
    Iterable<Country> findAll();
}
