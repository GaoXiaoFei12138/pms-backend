package com.example.personnel.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "validateCode")
public class ValidateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
}
