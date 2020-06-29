package com.codegym.demo.service.city;

import com.codegym.demo.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICityService {
    Iterable<City> getAll();

    Page<City> findAll(Pageable pageable);

    Optional<City> findById(Long id);

    Page<City> findAllByNameContaining (String productName, Pageable pageable);

    City save (City city);

    void remove(Long id);

    Page<City> findAllByCountry_Name(String name, Pageable pageable);
}
