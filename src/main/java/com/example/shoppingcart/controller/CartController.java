package com.example.shoppingcart.controller;


import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")

public class CartController {
    private final CartService cartService;


    public CartController(CartService cartService, ProductRepository productRepository) {
        this.cartService = cartService;

    }

    @GetMapping("/all-products")
    public List<Product> getAllProducts() {
        return cartService.getAllProducts();
    }

    @PostMapping("/post")
    public Product addProduct(@RequestBody Product product) {
        return cartService.addProduct(product);
    }

    @DeleteMapping("/delete/{name}")
    public String deleteProduct(@PathVariable ("name") String name ) {
        return cartService.deleteProduct(name);
    }


    @GetMapping("/total-cost")
    public double calculateTotalCost() {
        return  cartService.calculateCartTotal();

    }

    @GetMapping("/total-tax")
    public double calculateTotalTax() {
        return cartService.calculateTax();
    }

    @GetMapping("/total-with-tax")
    public double calculateTotalWithTax() {
        return cartService.calculateTotalWithTax();
    }


}
