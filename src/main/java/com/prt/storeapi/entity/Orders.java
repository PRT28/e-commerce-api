package com.prt.storeapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="total_price")
    private float totalPrice;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="user_id")
    private int userId;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<OrderList> orderList;

    public Orders(float totalPrice, LocalDateTime date, int userId) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.userId = userId;
    }

    public Orders() {}

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", user=" + userId +
                ", orderList=" + orderList +
                '}';
    }
}
