package com.store.MyOnlineStore.domain.entities.OrderAggregate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.store.MyOnlineStore.domain.entities.Address;
import com.store.MyOnlineStore.domain.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;
    @Embedded
    private Address shippingAddress;
    private Date orderDate = Date.from(Instant.now());
    // get the order items eagerly as there are no cases where we would need the order
    // without the items, so there is no need for an extra query each time
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = Collections.emptyList();
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    public Order () {}

    public Order(User user,
                 Address shippingAddress,
                 Date orderDate,
                 List<OrderItem> orderItems,
                 BigDecimal subtotal,
                 BigDecimal deliveryFee,
                 OrderStatus orderStatus) {
        this.user = user;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.orderStatus = orderStatus;
        this.orderItems = new ArrayList<>(orderItems.size());
        orderItems.forEach(this::addOrderItem);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Transient // use of transient so hibernate will not try to match it to a column in the db
    public BigDecimal getTotal() {
        return subtotal.add(deliveryFee);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        if (orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
    }

}
