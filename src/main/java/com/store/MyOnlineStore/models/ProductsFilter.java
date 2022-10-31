package com.store.MyOnlineStore.models;

import com.store.MyOnlineStore.domain.entities.Brand;
import com.store.MyOnlineStore.domain.entities.Type;

import java.util.Set;

public class ProductsFilter {
    private String productName;
    private Set<Type> types;
    private Set<Brand> brands;

    public ProductsFilter(){}

    public ProductsFilter(String productName, Set<Type> types, Set<Brand> brands) {
        this.productName = productName;
        this.types = types;
        this.brands = brands;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }
}
