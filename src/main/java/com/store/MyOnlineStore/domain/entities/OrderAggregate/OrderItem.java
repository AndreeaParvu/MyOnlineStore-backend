package com.store.MyOnlineStore.domain.entities.OrderAggregate;

import com.store.MyOnlineStore.domain.entities.BasketItem;
import com.store.MyOnlineStore.domain.entities.Product;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Embedded
    private ProductItemOrdered itemOrdered;
    private BigDecimal price;
    private long quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderItem() {}

    public OrderItem(BasketItem basketItem) {
        Product product = basketItem.getProduct();
        this.itemOrdered = new ProductItemOrdered(product);
        this.price = product.getPrice();
        this.quantity = basketItem.getQuantity();
    }

    public OrderItem(long id,
                     ProductItemOrdered itemOrdered,
                     BigDecimal price,
                     long quantity,
                     Order order) {
        this.id = id;
        this.itemOrdered = itemOrdered;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductItemOrdered getItemOrdered() {
        return itemOrdered;
    }

    public void setItemOrdered(ProductItemOrdered itemOrdered) {
        this.itemOrdered = itemOrdered;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
