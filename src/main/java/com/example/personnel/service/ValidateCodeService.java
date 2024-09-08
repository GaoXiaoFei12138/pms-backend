package com.example.personnel.service;

import com.example.personnel.model.ValidateCode;
import com.example.personnel.repository.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidateCodeService {

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    public List<String> getAllCodes() {
        return validateCodeRepository.findAll()
                .stream()
                .map(ValidateCode::getCode)
                .collect(Collectors.toList());
    }
}
