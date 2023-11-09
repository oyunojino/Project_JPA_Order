package com.example.jpaorder.repository;

import com.example.jpaorder.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepository {
  @PersistenceContext
  private final EntityManager em;

  public List<Order> findAll() {
    return em.createQuery("select o from Order o", Order.class).getResultList();
  }

//  # Optional 타입을 사용하는 이유
//  -> 만약 값이 없다면 null이 아니라 빈 객체가 넘어옴
  public Optional<Order> findById(Long orderId) {
    return Optional.ofNullable(em.find(Order.class, orderId));
  }

  public void save(Order order) {
    em.persist(order);
  }

  public void delete(Order order) {
    em.remove(order);
  }

  public List<Order> findOrdersByUserId(Long userId) {
    return null;
  }
}
