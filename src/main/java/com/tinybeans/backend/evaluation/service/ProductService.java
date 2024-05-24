package com.tinybeans.backend.evaluation.service;

import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Item> getAllProducts() {
        return productRepository.findAll();
    }

    public Item getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}