package com.tinybeans.backend.evaluation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.tinybeans.backend.evaluation.service.ProductService;
import com.tinybeans.backend.evaluation.data.entity.Item;

import java.util.List;

/**
 * @author danrodrigues
 * Date: 13/5/22
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Item> getAllProducts() {
        List<Item> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/{id}")
    public Item getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
