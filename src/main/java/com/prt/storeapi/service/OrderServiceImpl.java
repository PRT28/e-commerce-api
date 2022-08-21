package com.prt.storeapi.service;

import com.prt.storeapi.dao.OrderListDao;
import com.prt.storeapi.dao.OrdersDao;
import com.prt.storeapi.entity.OrderList;
import com.prt.storeapi.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private OrderListDao orderListDao;

    @Override
    @Transactional
    public List<Orders> getOrderByUserId(int userId) {
        List<Orders> order = ordersDao.findByUserId(userId);
        for (Orders o: order) {
            List<OrderList> orderList = orderListDao.findByOrderId(o.getId());
            o.setOrderList(orderList);
        }
        return order;
    }

    @Override
    @Transactional
    public void placeOrder(Orders orders, List<OrderList> orderList, int userId) {
        orders.setOrderList(orderList);
        orders.setUserId(userId);
        LocalDateTime date = LocalDateTime.now();
        orders.setDate(date);
        float total = 0;
        for (OrderList o: orderList){
            total = total + o.getPrice();
            o.setOrderId(orders.getId());
        }
        orders.setTotalPrice(total);
        ordersDao.save(orders);
    }

    @Override
    @Transactional
    public void cancelOrder(int id) {
        ordersDao.deleteById(id);
    }

    public Orders getOrderById(int id) {
        Orders order = ordersDao.findById(id);
        return order;
    }


}
