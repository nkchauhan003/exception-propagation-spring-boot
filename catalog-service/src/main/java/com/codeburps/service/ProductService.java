package com.codeburps.service;

import com.codeburps.exception.ProductNotFoundException;
import com.codeburps.model.Product;

public interface ProductService {
    public Product getProduct(int productId) throws ProductNotFoundException;
}
