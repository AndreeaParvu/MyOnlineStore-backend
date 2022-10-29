package com.store.MyOnlineStore.models;

public class ItemRequest {
    private long productId;
    private long quantity;

    public ItemRequest() {}

    public ItemRequest(long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
