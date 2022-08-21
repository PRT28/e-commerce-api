package com.prt.storeapi.service;

import com.prt.storeapi.entity.OrderList;
import com.prt.storeapi.entity.Orders;

import java.util.List;

public interface OrderService {
    public List<Orders> getOrderByUserId(int userId);

    public void placeOrder(Orders orders, List<OrderList> orderList, int userId);

    public void cancelOrder(int orderId);

    public Orders getOrderById(int id);
}
