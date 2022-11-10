package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.OrderAggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
