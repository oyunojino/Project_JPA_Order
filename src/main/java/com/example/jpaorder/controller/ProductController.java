package com.example.jpaorder.controller;

import com.example.jpaorder.dto.ProductUpdateDto;
import com.example.jpaorder.entity.Product;
import com.example.jpaorder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
  private final ProductService productService;

  @GetMapping("/list")
  public String getAllProducts(Model model){
    List<Product> allProducts = productService.getAllProducts();
    model.addAttribute("products", allProducts);
    return "product/productList";
  }
  @GetMapping("/add")
  public String addProduct() {
    return "product/productForm";
  }
  @PostMapping("/add")
  public String addProduct(@ModelAttribute Product product) {
    productService.addProduct(product);
    return "redirect:/product/list";
  }
  @GetMapping("/info/{productId}")
  public String getProductInfo(@PathVariable long productId, Model model) {
    Product productInfo = productService.getProductInfo(productId);
    model.addAttribute("product", productInfo);
    return "product/productInfo";
  }

//  ERROR ) productId -> null
//  -> HTML 파일에서 th:field로 변경시켜줌
  @PostMapping("/update")
  public String updateProduct(@ModelAttribute ProductUpdateDto productUpdate) {
    productService.updateProduct(productUpdate);
    return "redirect:/product/list";
  }

  @GetMapping("/update/{productId}")
  public String updateProduct(@PathVariable long productId, Model model) {
    Product productInfo = productService.getProductInfo(productId);
    model.addAttribute("product", productInfo);
    return "product/productUpdate";
  }

//  ERROR ) delete 수정
  @GetMapping("/list")
  public String deleteProduct(@RequestParam long productId) {
    productService.deleteProduct(productId);
    return "redirect:/product/list";
  }

}
