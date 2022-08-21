package com.prt.storeapi.service;

import com.prt.storeapi.entity.User;

public interface UserService {

    public User getUserByEmail(String email);

    public void saveUser(User user);

    public  User getUserById(int id);

    public void deleteUserById(int id);
}
