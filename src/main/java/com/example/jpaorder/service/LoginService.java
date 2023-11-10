package com.example.jpaorder.service;

import com.example.jpaorder.dto.UserLoginDto;
import com.example.jpaorder.entity.User;
import com.example.jpaorder.repository.JpaUserRepository;
import com.example.jpaorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
  private final JpaUserRepository userRepository;

  public User login(UserLoginDto userLoginDto) {
    Optional<User> byEmail = userRepository.findByEmail(userLoginDto.getEmail());
    if(!byEmail.isEmpty()) {
      User user = byEmail.get();
      if(user.getPassword().equals(userLoginDto.getPassword())) {
        return user;
      }
    }
    return null;
  }
}
