package com.codegym.demo.repository;

import com.codegym.demo.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    Page<City> findAllByNameContaining (String productName, Pageable pageable);

    Page<City> findAllByCountry_Name(String name, Pageable pageable);
}
