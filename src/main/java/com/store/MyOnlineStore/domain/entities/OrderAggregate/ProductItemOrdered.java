package com.store.MyOnlineStore.domain.entities.OrderAggregate;

import javax.persistence.Embeddable;

@Embeddable
public class ProductItemOrdered {
    private long productId;
    private String name;
    private String pictureUrl;

    public ProductItemOrdered() {}

    public ProductItemOrdered(long productId, String name, String pictureUrl) {
        this.productId = productId;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
