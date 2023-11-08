package com.example.jpaorder.service;

import com.example.jpaorder.dto.ProductUpdateDto;
import com.example.jpaorder.entity.Product;
import com.example.jpaorder.repository.JpaProductRepository;
import com.example.jpaorder.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final JpaProductRepository jpaProductRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
  public Product getProductInfo(long productId) {
    return productRepository.findById(productId).get();
  }

  public void addProduct(Product product) {
    productRepository.save(product);
  }

  public void deleteProduct(long productId) {
    productRepository.deleteById(productId);
  }

  public void updateProduct(ProductUpdateDto productUpdateDto) {
    Product product = productRepository.findById(productUpdateDto.getProductId()).get();
    product.setName(productUpdateDto.getName());
    product.setPrice(productUpdateDto.getPrice());
    productRepository.save(product);
  }

}
