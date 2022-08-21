package com.prt.storeapi.controller;


import com.prt.storeapi.entity.ApiResponse;
import com.prt.storeapi.entity.Whishlist;
import com.prt.storeapi.service.WhishlistService;
import com.prt.storeapi.util.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/whishlist")
public class WhishlistController {

    @Autowired
    private RequestMeta requestMeta;

    @Autowired
    private WhishlistService whishlistService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> getWhishlist() throws Exception {
        ApiResponse response = new ApiResponse();
        int id = requestMeta.getId();
        try {
            List<Whishlist> list = whishlistService.getWhishlist(id);
            response.setStatus(HttpStatus.OK.value());
            response.setData(list);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveItem(@RequestBody String itemId) throws Exception {
        ApiResponse response = new ApiResponse();
        int userId = requestMeta.getId();
        try {
            Whishlist item = new Whishlist(itemId, userId);
            whishlistService.saveItem(item);
            response.setStatus(HttpStatus.OK.value());
            response.setData("Item Saved Successfully");
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> deleteItem(@RequestParam int id) throws Exception {
        ApiResponse response = new ApiResponse();
        int userId = requestMeta.getId();
        try {
            whishlistService.deleteItem(id);
            response.setStatus(HttpStatus.OK.value());
            response.setData("item removed successfully");
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/clearAll", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> clearList() throws Exception {
        ApiResponse response = new ApiResponse();
        int userId = requestMeta.getId();
        try {
            whishlistService.clearList(userId);
            response.setStatus(HttpStatus.OK.value());
            response.setData("item removed successfully");
        } catch (Exception e) {
            throw new Exception(e);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
