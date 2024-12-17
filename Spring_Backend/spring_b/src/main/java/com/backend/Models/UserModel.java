package com.backend.Models;

import org.springframework.data.annotation.Id;

import lombok.*;

@Data
public class UserModel {
    @Id
    private Integer ID;

    private String First_Name;
    private String Last_Name;
    
}
