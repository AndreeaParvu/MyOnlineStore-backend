package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.Brand;
import com.store.MyOnlineStore.domain.entities.Product;
import com.store.MyOnlineStore.domain.entities.Type;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("select distinct product.type from Product as product")
    Set<Type> findDistinctProductTypes();

    @Query("select distinct product.brand from Product as product")
    Set<Brand> findDistinctProductBrands();
    List<Product> findByIdIn(Set<Long> productIds);
}
