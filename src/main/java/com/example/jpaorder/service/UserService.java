package com.example.jpaorder.service;

import com.example.jpaorder.entity.User;
import com.example.jpaorder.entity.UserType;
import com.example.jpaorder.repository.JpaUserRepository;
import com.example.jpaorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final JpaUserRepository userRepository;
  public void addUser(User user) {
    Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
    if(!byEmail.isEmpty()) {
      throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
    }
    user.setUserType(UserType.BASIC);
    userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long userId) {
    return userRepository.findById(userId);
  }
}
