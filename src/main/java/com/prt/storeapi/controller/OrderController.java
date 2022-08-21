package com.prt.storeapi.controller;

import com.prt.storeapi.entity.ApiResponse;
import com.prt.storeapi.entity.OrderList;
import com.prt.storeapi.entity.Orders;
import com.prt.storeapi.entity.User;
import com.prt.storeapi.service.OrderService;
import com.prt.storeapi.service.UserService;
import com.prt.storeapi.util.JwtUtil;
import com.prt.storeapi.util.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestMeta requestMeta;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> getOrders() throws Exception {
        ApiResponse response = new ApiResponse();
        int userId = requestMeta.getId();
        try {
            List<Orders> order= orderService.getOrderByUserId(userId);
            response.setStatus(HttpStatus.OK.value());
            response.setData(order);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody List<OrderList> orderList) throws Exception {
        ApiResponse response = new ApiResponse();
        int userId = requestMeta.getId();
        try {
            Orders order = new Orders();
            orderService.placeOrder(order, orderList, userId);
            response.setData("Order Placed Successfully");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> cancelOrder(@RequestParam int orderId) throws Exception {
        ApiResponse response = new ApiResponse();
        try {
            orderService.cancelOrder(orderId);
            response.setStatus(HttpStatus.OK.value());
            response.setData("Order Canceled Successfully");
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> search(@RequestParam int id) throws Exception {
        ApiResponse response = new ApiResponse();
        try {
            Orders order = orderService.getOrderById(id);
            response.setStatus(HttpStatus.OK.value());
            response.setData(order);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
