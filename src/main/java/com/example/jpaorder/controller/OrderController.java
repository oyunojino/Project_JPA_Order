package com.example.jpaorder.controller;

import com.example.jpaorder.dto.OrderDto;
import com.example.jpaorder.entity.Order;
import com.example.jpaorder.entity.OrderProduct;
import com.example.jpaorder.entity.Product;
import com.example.jpaorder.exception.NoEnoughStockException;
import com.example.jpaorder.service.OrderService;
import com.example.jpaorder.service.ProductService;
import com.example.jpaorder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
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
  @GetMapping("/info/{orderId}")
  public String getOrderInfo(@PathVariable Long orderId, Model model) {
    Order order = orderService.getOrderInfo(orderId);
    order.getOrderProducts();
    model.addAttribute("order", order);
    return "order/orderInfo";
  }

  @GetMapping("/add")
  public String addOrder(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    model.addAttribute("products", productService.getAllProducts());
    return "order/orderForm";
  }

  @PostMapping("/add")
  public String addOrder(@ModelAttribute OrderDto orderDto) throws NoEnoughStockException { // REFACTORY )
    orderService.addOrder(orderDto);
    return "redirect:/order/list";
  }

}
