package com.store.MyOnlineStore.service;

import com.store.MyOnlineStore.domain.entities.Basket;
import com.store.MyOnlineStore.domain.entities.OrderAggregate.Order;
import com.store.MyOnlineStore.domain.entities.OrderAggregate.OrderItem;
import com.store.MyOnlineStore.domain.entities.OrderAggregate.OrderStatus;
import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.domain.entities.User;
import com.store.MyOnlineStore.domain.repository.BasketRepository;
import com.store.MyOnlineStore.domain.repository.OrderRepository;
import com.store.MyOnlineStore.domain.repository.ProductRepository;
import com.store.MyOnlineStore.domain.repository.UserRepository;
import com.store.MyOnlineStore.models.CreateOrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        BasketRepository basketRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }
    @Transactional(readOnly = true)
    public List<Order> findAllOrders(long userId) {
        return orderRepository.finByUser_Id(userId);
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(long userId,
                             String buyerId,
                             CreateOrderRequest createOrderRequest) {
        User currentUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for id " + userId));


        Basket basket = basketRepository
                .findByIdWithItems(buyerId)
                .orElseThrow(() -> new RuntimeException("Basket not found for id " + buyerId));

        List<OrderItem> orderItems = basket
                .getItems()
                .stream()
                .map(OrderItem::new)
                .collect(Collectors.toList());

        Order order = fillOrder(orderItems, currentUser, createOrderRequest);
        reserveProductQuantity(orderItems);
        clearBasketItems(basket);
        saveShippingAddressOnUser(currentUser, createOrderRequest);

        return order;
    }

    private Order fillOrder(List<OrderItem> orderItems, User currentUser, CreateOrderRequest createOrderRequest) {
        long subtotal = orderItems
                .stream()
                .mapToLong(orderItem ->
                        orderItem
                                .getPrice()
                                .multiply(new BigDecimal(orderItem.getQuantity()))
                                .longValue())
                .sum();

        long deliveryFee = subtotal > 500 ? 0 : 20;

        Order order = new Order(currentUser,
                createOrderRequest.getAddress(),
                new Date(),
                orderItems,
                new BigDecimal(subtotal),
                new BigDecimal(deliveryFee),
                OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    private void saveShippingAddressOnUser(User currentUser, CreateOrderRequest createOrderRequest) {
        if (createOrderRequest.isStoreAddress()) {
            currentUser.setAddress(currentUser.getAddress());
            userRepository.save(currentUser);
        }
    }

    private void clearBasketItems(Basket basket) {
        basket.getItems().clear();
        basket.setItems(Collections.emptySet());
        basketRepository.save(basket);
    }

    private void reserveProductQuantity(List<OrderItem> orderItems) {
        Map<Long, OrderItem> productOrderItemMapping = orderItems
                .stream()
                .collect(Collectors
                        .toMap(orderItem -> orderItem.getItemOrdered().getProductId(), orderItem -> orderItem));


        if (!productOrderItemMapping.keySet().isEmpty()) {
            List<Product> products = productRepository.findByIdIn(productOrderItemMapping.keySet());
            products
                    .forEach(product -> {
                        OrderItem orderItem = productOrderItemMapping.get(product.getId());
                        product.setQuantityInStock(product.getQuantityInStock() - orderItem.getQuantity());
                    });

            productRepository.saveAll(products);
        }

    }

}
