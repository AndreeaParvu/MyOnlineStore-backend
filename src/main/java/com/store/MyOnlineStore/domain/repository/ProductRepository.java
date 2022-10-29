package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
