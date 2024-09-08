package com.example.personnel.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PersonnelRequest {
    private Long id;
    private String name;
    private String imageUrl;
    private MultipartFile file;
}
