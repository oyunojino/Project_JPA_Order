package com.example.jpaorder.repository;

import com.example.jpaorder.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository {
  @PersistenceContext
  private final EntityManager em;

  public void save(User user) {
    em.persist(user);
  }

  public List<User> findAll() {
    return em.createQuery("SELECT u FROM User u", User.class).getResultList();
  }

  public User findById(Long userId) {
    return em.find(User.class, userId);
  }

  public void remove(User user) {
    em.remove(user);
  }
}
