package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findBasketByBuyerId(String buyerId);
    @Query("select b from Basket b inner join b.items bi where b.buyerId = :buyerId")
    Optional<Basket> findByIdWithItems(@Param("buyerId") String buyerId);
}
