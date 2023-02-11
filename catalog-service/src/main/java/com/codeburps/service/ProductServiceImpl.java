package com.codeburps.service;

import com.codeburps.exception.ProductNotFoundException;
import com.codeburps.model.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    // TODO should come from database
    private Product product1 = new Product(101, "name-" + 101, (Math.random() * (500 - 50)) + 50);
    private Product product2 = new Product(102, "name-" + 102, (Math.random() * (500 - 50)) + 50);
    private Product product3 = new Product(103, "name-" + 103, (Math.random() * (500 - 50)) + 50);

    // TODO should come from database
    private Map<Integer, Product> productRepository = Map.of(101, product1, 102, product2, 103, product3);

    @Override
    public Product getProduct(int productId) throws ProductNotFoundException {
        if (!productRepository.containsKey(productId))
            throw new ProductNotFoundException("Product not found for: " + productId);
        return new Product(productId, "name-" + productId, (Math.random() * (500 - 50)) + 50);
    }
}
