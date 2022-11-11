package com.store.MyOnlineStore.domain.entities.OrderAggregate;

import com.store.MyOnlineStore.domain.entities.Product;

import javax.persistence.Embeddable;

@Embeddable
public class ProductItemOrdered {
    private long productId;
    private String name;
    private String pictureUrl;

    public ProductItemOrdered() {}

    public ProductItemOrdered(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.pictureUrl = product.getPictureUrl();
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
