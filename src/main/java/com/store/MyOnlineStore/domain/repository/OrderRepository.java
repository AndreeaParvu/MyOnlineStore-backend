package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.OrderAggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o inner join o.user u where u.id = :userId")
    List<Order> finByUserId(@Param("userId") long userId);
}
