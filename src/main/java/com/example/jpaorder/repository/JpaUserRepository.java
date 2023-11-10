package com.example.jpaorder.repository;

import com.example.jpaorder.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

  public Optional<User> findByEmail(String email) {
//    # 결과의 개수를 알 수 없기 때문에 list로 받아서 stream 처리해야함
    return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
        .setParameter("email", email).getResultList().stream().filter(u -> u.getEmail().equals(email)).findFirst();
  }
}
