package com.example.personnel.controller;

import com.example.personnel.model.Personnel;
import com.example.personnel.model.PersonnelRequest;
import com.example.personnel.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/personnel")
@CrossOrigin
public class PersonnelController {

    @Autowired
    private PersonnelRepository personnelRepository;
    private final String UPLOAD_DIR = "./uploads/images/";

    @GetMapping
    public Page<Personnel> getUsers(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        if (query == null || query.isEmpty()) {
            // 当query为空时，按页查询所有数据  
            return personnelRepository.findAll(pageable);
        } else {
            // 当query不为空时，按名字查询  
            return personnelRepository.findByNameIgnoreCase(query, pageable);
        }
    }

    @PostMapping
    public ResponseEntity<String> addUser(@ModelAttribute PersonnelRequest personnelRequest) {
        try {
            MultipartFile file = personnelRequest.getFile();
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            String imageUrl = "/uploads/images/" + fileName; // 注意这里
            Personnel personnel = new Personnel();
            personnel.setName(personnelRequest.getName());
            personnel.setImageUrl(imageUrl); // 使用相对路径
            personnelRepository.save(personnel);

            return ResponseEntity.ok("上传成功，URL: " + path.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> editUser(@PathVariable Long id, @ModelAttribute  PersonnelRequest personnelRequest) {
        MultipartFile file = personnelRequest.getFile();
        Personnel personnel = personnelRepository.findById(id).orElse(null);
        if (personnel != null) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String imageUrl = "/uploads/images/" + fileName; // 注意这里
            personnel.setImageUrl(imageUrl);
            final Personnel updatedPersonnel = personnelRepository.save(personnel);
            return ResponseEntity.ok(updatedPersonnel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (personnelRepository.existsById(id)) {
            personnelRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}  