package com.example.personnel.service;

import com.example.personnel.model.Personnel;
import com.example.personnel.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    public Personnel savePersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    public Page<Personnel> getAllPersonnels(Pageable pageable) {
        return personnelRepository.findAll(pageable);
    }

    public Page<Personnel> findByNameIgnoreCase(String name, Pageable pageable) {
        return personnelRepository.findByNameIgnoreCase(name, pageable);
    }

    public Personnel updatePersonnel(Long id, Personnel personnel) {
        personnel.setId(id);
        return personnelRepository.save(personnel);
    }

    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }
}