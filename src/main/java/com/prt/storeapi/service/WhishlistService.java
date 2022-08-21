package com.prt.storeapi.service;

import com.prt.storeapi.entity.Whishlist;

import java.util.List;

public interface WhishlistService {

    public List<Whishlist> getWhishlist(int userId);

    public void saveItem(Whishlist item);

    public void deleteItem(int id);

    public void clearList(int id);
}
