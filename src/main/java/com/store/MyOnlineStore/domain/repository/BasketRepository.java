package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.Basket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends PagingAndSortingRepository<Basket, Long> {
    Optional <Basket> findBasketByBuyerId(String buyerId);
}
