package com.prt.storeapi.dao;

import com.prt.storeapi.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListDao extends JpaRepository<OrderList, Integer> {

    public List<OrderList> findByOrderId(int orderId);
}
