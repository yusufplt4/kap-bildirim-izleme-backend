package com.example.kap.repository;

import com.example.kap.entity.HimFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HimFilterRepository extends JpaRepository<HimFilter, Long> {

    Optional<HimFilter> findByFilterNameIgnoreCase(String filterName);
}