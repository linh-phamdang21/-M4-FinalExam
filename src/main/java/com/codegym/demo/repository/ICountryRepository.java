package com.codegym.demo.repository;

import com.codegym.demo.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICountryRepository extends PagingAndSortingRepository<Country, Long> {
    Page<Country> findAllByNameContaining(String typeName, Pageable pageable);
}
