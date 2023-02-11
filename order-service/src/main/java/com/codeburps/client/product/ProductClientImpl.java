package com.codeburps.client.product;

import com.codeburps.client.product.model.Product;
import com.codeburps.exception.AppException;
import com.codeburps.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component @Slf4j public class ProductClientImpl implements ProductClient {
    @Value("${api.catalog.baseurl}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProduct(int productId) {
        Product product = null;
        try {
            product = restTemplate.getForObject(apiBaseUrl + "/product/" + productId, Product.class);
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                throw new ProductNotFoundException("Service is currently unavailable !!!");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException(e.getResponseBodyAsString());
            } else {
                throw new ProductNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        return product;
    }
}
