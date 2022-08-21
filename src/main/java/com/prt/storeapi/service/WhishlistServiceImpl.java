package com.prt.storeapi.service;

import com.prt.storeapi.dao.WhishlistDao;
import com.prt.storeapi.entity.Whishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WhishlistServiceImpl implements WhishlistService{

    @Autowired
    private WhishlistDao whishlistDao;

    @Override
    @Transactional
    public List<Whishlist> getWhishlist(int userId) {
        List<Whishlist> whishlist = whishlistDao.findByUserId(userId);
        return whishlist;
    }

    @Override
    @Transactional
    public void saveItem(Whishlist item) {
        whishlistDao.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(int id) {
        whishlistDao.deleteById(id);
    }

    @Override
    @Transactional
    public void clearList(int id) {
        whishlistDao.clearFullList(id);
    }
}
