package com.backend.Models;

import org.springframework.data.annotation.Id;
import lombok.*;


@Data
public class ProductModel {
    @Id
    private Integer ID;

    private String Product_Name;
    private Float Product_Price;
    private String Product_Description;
    
}