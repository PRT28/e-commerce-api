package com.prt.storeapi.service;


import com.prt.storeapi.dao.UserDao;
import com.prt.storeapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        User user = userDao.findByEmail(email);
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }
}
