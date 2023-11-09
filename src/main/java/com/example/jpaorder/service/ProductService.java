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

//  ERROR ) Optional 타입을 Product 타입으로 변경 하기 위해 .get() 추가
  public void updateProduct(ProductUpdateDto productUpdateDto) {
//    # 재고 수량은 수정불가하기 때문에 ProductUpdateDto 사용
    Product product = productRepository.findById(productUpdateDto.getProductId()).get();
    product.setName(productUpdateDto.getName());
    product.setPrice(productUpdateDto.getPrice());
//    product.setQuantity(productUpdateDto.getQuantity());
    productRepository.save(product);
  }

}
