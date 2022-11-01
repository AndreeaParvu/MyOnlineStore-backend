package com.store.MyOnlineStore.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String buyerId;
    @OneToMany(mappedBy = "basket", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonManagedReference
    private Set<BasketItem> items = new HashSet<>();

    public Basket() {
    }

    public Basket(String buyerId, Set<BasketItem> items) {
        this.buyerId = buyerId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Set<BasketItem> getItems() {
        return items == null ? Collections.emptySet() : items;
    }

    public void setItems(Set<BasketItem> items) {
        this.items.clear();
        this.items = new HashSet<>(items);
    }
}
