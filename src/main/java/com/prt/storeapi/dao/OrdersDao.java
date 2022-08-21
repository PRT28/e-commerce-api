package com.prt.storeapi.dao;

import com.prt.storeapi.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer> {

    public Orders findById(int id);

    public List<Orders> findByUserId(int userId);
}
