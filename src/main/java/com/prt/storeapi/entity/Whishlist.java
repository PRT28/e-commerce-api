package com.prt.storeapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "whishlist")
public class Whishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "user_id")
    private int userId;

    public Whishlist(String itemId, int userId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    public Whishlist() {
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Whishlist{" +
                "itemId='" + itemId + '\'' +
                ", userId=" + userId +
                '}';
    }
}
