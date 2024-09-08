package com.example.personnel.repository;

import com.example.personnel.model.ValidateCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidateCodeRepository extends JpaRepository<ValidateCode, Long> {
}
