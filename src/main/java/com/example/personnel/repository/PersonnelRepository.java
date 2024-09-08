package com.example.personnel.repository;

import com.example.personnel.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Page<Personnel> findByNameIgnoreCase(String name, Pageable pageable);
}