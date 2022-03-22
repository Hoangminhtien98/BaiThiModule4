package com.example.tien.repository;

import com.example.tien.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository  extends PagingAndSortingRepository<City, Long> {
    Page<City> findAllByNameContaining(String name, Pageable pageable);
}
