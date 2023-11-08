package com.example.jpaorder.service;

import com.example.jpaorder.entity.User;
import com.example.jpaorder.entity.UserType;
import com.example.jpaorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public void addUser(User user) {
    user.setUserType(UserType.BASIC);
    userRepository.save(user);
  }
}
