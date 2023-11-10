package com.example.jpaorder.controller;

import com.example.jpaorder.dto.UserLoginDto;
import com.example.jpaorder.entity.User;
import com.example.jpaorder.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
  private final LoginService loginService;

  @GetMapping
  public String login() {
    return "login/loginForm";
  }

  @PostMapping()
  public String login(@ModelAttribute UserLoginDto userLoginDto, HttpServletResponse response) {
    User loginUser = loginService.login(userLoginDto);
    if(loginUser == null) {
      return "redirect:/login";
    }
//    # 로그인에 성공시 쿠키설정
    Cookie cookie = new Cookie("userId", loginUser.getUserId().toString());
    cookie.setPath("/");
    cookie.setMaxAge(60);

    response.addCookie(cookie);
    return "redirect:/order/add";
  }
}
