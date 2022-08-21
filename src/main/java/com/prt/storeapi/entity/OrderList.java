package com.prt.storeapi.entity;

import javax.persistence.*;
import javax.persistence.criteria.Order;

@Entity
@Table(name = "order_list")
public class OrderList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="item_id")
    private String itemId;

    @Column(name="qty")
    private int quantity;

    @Column(name="price")
    private float price;

    @Column(name="order_id")
    private int orderId;

    public OrderList(String itemId, int quantity, float price) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderList() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "id=" + id +
                ", itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orders=" + orderId +
                '}';
    }
}
