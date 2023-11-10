package com.example.jpaorder.controller;

import com.example.jpaorder.dto.OrderDto;
import com.example.jpaorder.entity.Order;
import com.example.jpaorder.entity.OrderProduct;
import com.example.jpaorder.entity.Product;
import com.example.jpaorder.entity.User;
import com.example.jpaorder.exception.NoEnoughStockException;
import com.example.jpaorder.service.OrderService;
import com.example.jpaorder.service.ProductService;
import com.example.jpaorder.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderController {
  private final OrderService orderService;
  private final ProductService productService;
  private final UserService userService;

  @GetMapping("/list")
  public String getAllOrders(Model model) {
    List<Order> allOrders = orderService.getAllOrders();
    model.addAttribute("orders", allOrders);
    return "order/orderList";
  }

  @GetMapping("/list/{userId}")
  public String getAllOrdersByUserId(@PathVariable Long userId, Model model) {
    List<Order> allOrdersByUserId = orderService.getAllOrdersByUserId(userId);
    model.addAttribute("orders", allOrdersByUserId);
    return "order/orderList";
  }

  // ERROR )
  @GetMapping("/info/{userId}")
  public String getOrderInfo(@PathVariable Long userId, Model model) {
    Order order = orderService.getOrderInfo(userId);
//    order.getOrderProducts();
    model.addAttribute("order", order);
    return "order/orderInfo";
  }

  @GetMapping("/add")
  public String addOrder(Model model, HttpServletRequest request) {
//    # 로그인한 사용자의 정보를 쿠키로부터 얻어오기
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      log.info("log_cookie.name => " + cookie.getName());
//      System.out.println("sout_cookie.name => " + cookie.getName());
      if(cookie.getName().equals("userId")) {
        Long userId = Long.parseLong(cookie.getValue());
        User user = userService.getUserById(userId);
        model.addAttribute("user",user);
      }
    }
//    # 사용자의 정보를 model에 넘겨주기
    model.addAttribute("users", userService.getAllUsers());
    model.addAttribute("products", productService.getAllProducts());
    return "order/orderForm";
  }

  @PostMapping("/add")
  public String addOrder(@ModelAttribute OrderDto orderDto) throws NoEnoughStockException { // REFACTORY )
    orderService.addOrder(orderDto);
    return "redirect:/order/list";
  }

  @GetMapping("/cancel/{orderId}")
  public String cancelOrder(@PathVariable Long orderId) {
    orderService.cancelOrder(orderId);
    return "redirect:/order/list";
  }
}
