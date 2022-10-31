package com.store.MyOnlineStore.models;

import com.store.MyOnlineStore.domain.entities.Brand;
import com.store.MyOnlineStore.domain.entities.Type;


import java.util.Set;

public class ProductFilterOptions {
    private Set<Type> types;
    private Set<Brand> brands;

    public ProductFilterOptions() {}

    public ProductFilterOptions(Set<Type> types, Set<Brand> brands) {
        this.types = types;
        this.brands = brands;
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
