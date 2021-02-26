package com.example.demo.city.repository;

import com.example.demo.city.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CityRepository extends PagingAndSortingRepository<City,Long> {
    Page<City> findAllByNameContaining(String name , Pageable pageable);
}
