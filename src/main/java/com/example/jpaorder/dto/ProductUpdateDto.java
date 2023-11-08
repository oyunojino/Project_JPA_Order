package com.example.jpaorder.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDto {
    private Long productId;
    private String name;
    private int price;
}
