package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {

}
