package com.codegym.demo.service.country;

import com.codegym.demo.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICountryService {

    Iterable<Country> getAll();

    Page<Country> findAll(Pageable pageable);

    Optional<Country> findById(Long id);

    Page<Country> findAllByNameContaining(String name, Pageable pageable);

    Country save(Country country);

    void remove(Long id);
}
