package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.Basket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends PagingAndSortingRepository<Basket, Long> {
    Optional<Basket> findBasketByBuyerId(String buyerId);
    @Query("select b Basket b inner join b.items bi where b.id = :buyerId")
    Optional<Basket> findByIdWithItems(@Param("buyerId") String buyerId);
}
