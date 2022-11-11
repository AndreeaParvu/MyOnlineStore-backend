package com.store.MyOnlineStore.models;

import com.store.MyOnlineStore.domain.entities.Address;

public class CreateOrderRequest {
    private boolean storeAddress;
    private Address address;

    public boolean isStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(boolean storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
