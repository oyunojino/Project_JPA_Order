package com.example.jpaorder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
  private Long userId;
  private Long productId;
  private int orderQuantity;
}
