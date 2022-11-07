package com.store.MyOnlineStore.domain.entities.OrderAggregate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String buyerId;
    private ShippingAddress shippingAddress;
    private Date orderDate = Date.from(Instant.now());
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    private long subtotal;
    private long deliveryFee;
    private OrderStatus orderStatus = OrderStatus.PENDING;

    public Order () {}

    public Order(long id, String buyerId, ShippingAddress shippingAddress, Date orderDate, List<OrderItem> orderItems, long subtotal, long deliveryFee, OrderStatus orderStatus) {
        this.id = id;
        this.buyerId = buyerId;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.subtotal = subtotal;
        this.deliveryFee = deliveryFee;
        this.orderStatus = orderStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
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

    public long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }

    public long getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(long deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getTotal() {
        return subtotal + deliveryFee;
    }
}
