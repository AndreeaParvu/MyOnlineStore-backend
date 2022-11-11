package com.store.MyOnlineStore.models;

import com.store.MyOnlineStore.domain.entities.Address;

public class CreateOrderRequest {
    private boolean saveAddress;
    private Address shippingAddress;

    public boolean isSaveAddress() {
        return saveAddress;
    }

    public void setSaveAddress(boolean saveAddress) {
        this.saveAddress = saveAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
