package com.prt.storeapi.util;

public class RequestMeta {

    private String name;

    private String email;

    private int id;

    public RequestMeta(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public RequestMeta() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
