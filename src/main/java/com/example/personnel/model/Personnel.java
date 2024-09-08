package com.example.personnel.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "personnel")
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
}