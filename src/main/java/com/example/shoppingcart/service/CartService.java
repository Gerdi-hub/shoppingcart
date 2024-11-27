package com.example.shoppingcart.service;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {
    private ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String deleteProduct(String name) {
        productRepository.deleteByName(name);
        return "Product deleted";
    }


    public double calculateCartTotal() {
        List<Product> products = productRepository.findAll();
        double total = 0.0;
        for (Product product : products) {
            total += product.totalPrice();
        }
        return total;

    }

    public double calculateTax(){
        double total = calculateCartTotal() * 0.22;
        return total;
    }

    public double applyDiscount(boolean isMember) {
        double discount = 0;
        if (isMember) {
            double totalSum = calculateCartTotal() + calculateTax();
            discount = totalSum * 0.1;
        }
        return discount;
    }

    public double calculateTotalWithTax (){
        return calculateCartTotal() + calculateTax();
    }

}
