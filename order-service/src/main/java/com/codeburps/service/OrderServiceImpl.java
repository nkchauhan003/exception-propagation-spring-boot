package com.codeburps.service;

import com.codeburps.client.product.ProductClient;
import com.codeburps.client.product.model.Product;
import com.codeburps.dto.OrderDto;
import com.codeburps.exception.AppException;
import com.codeburps.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Override
    public Order placeOrder(OrderDto orderDto) {
        var customerId = ThreadLocalRandom.current().nextInt(100, 200 + 1);
        double orderValue = 0.0;
        for (var item : orderDto.getItems()) {
            Product product = productClient.getProduct(item.getProductId());
            orderValue += product.getPrice() * item.getQuantity();
        }
        var order = new Order(
                ThreadLocalRandom.current().nextInt(100, 200 + 1), customerId, orderDto.getItems(), orderValue);
        return order;
    }
}
