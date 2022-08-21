package com.prt.storeapi.dao;

import com.prt.storeapi.entity.Whishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WhishlistDao extends JpaRepository<Whishlist, Integer> {

    public List<Whishlist> findByUserId(int userId);

    @Modifying
    @Query("delete from Whishlist b where b.userId=:id")
    void clearFullList(@Param("id") int id);
}
