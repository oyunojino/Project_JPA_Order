package com.example.jpaorder.repository;

import com.example.jpaorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
