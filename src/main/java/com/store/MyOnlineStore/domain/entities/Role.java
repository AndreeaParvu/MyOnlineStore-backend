package com.store.MyOnlineStore.domain.entities;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private CommerceRole commerceRole;

    public Role() {}

    public Role(long id, CommerceRole commerceRole) {
        this.id = id;
        this.commerceRole = commerceRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CommerceRole getCommerceRole() {
        return commerceRole;
    }

    public void setCommerceRole(CommerceRole commerceRole) {
        this.commerceRole = commerceRole;
    }
}
