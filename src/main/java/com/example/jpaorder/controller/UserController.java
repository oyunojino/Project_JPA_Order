package com.example.jpaorder.controller;

import com.example.jpaorder.entity.User;
import com.example.jpaorder.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/add")
  public String addUser() {
    return "user/userForm";
  }

  @PostMapping("/add")
  public String addUser(@ModelAttribute User user) {
    userService.addUser(user);
    return "redirect:/product/list";
  }
}
