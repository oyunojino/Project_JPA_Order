package com.example.jpaorder.repository;

import com.example.jpaorder.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
// # EntityManager의 생성자를 작성할 필요 없음
public class JpaProductRepository {
//  @Autowired
//  public JpaProductRepository(EntityManager em) {this.em = em;}

  @PersistenceContext
//  # @PersistenceContext
//   -> 연속성 컨텍스트(=EntityManager)를 Bean으로 등록
  private EntityManager em;

  public void save(Product product) {
    em.persist(product);
  }

  public List<Product> findAll() {
    return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
  }

  public Product findById(Long productId) {
    return em.find(Product.class, productId);
  }

  public void remove(Product product) {
    em.remove(product);
  }

}
