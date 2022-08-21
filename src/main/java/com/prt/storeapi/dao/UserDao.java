package com.prt.storeapi.dao;

import com.prt.storeapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    public User findById(int id);
}
