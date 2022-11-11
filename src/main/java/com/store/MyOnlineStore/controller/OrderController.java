package com.store.MyOnlineStore.controller;

import com.store.MyOnlineStore.domain.entities.OrderAggregate.Order;
import com.store.MyOnlineStore.models.CreateOrderRequest;
import com.store.MyOnlineStore.security.SecurityUserDetails;
import com.store.MyOnlineStore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("commerce/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        // this is an endpoint which requires authentication so the principal should
        // never be empty/null
        SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(orderService.findAllOrders(userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathParam("id") long id) {
        return orderService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @CookieValue(value = "buyerId") String buyerId,
            @RequestBody CreateOrderRequest createOrderRequest
    ) {
        SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(orderService.createOrder(userDetails.getId(), buyerId, createOrderRequest));
    }
}
