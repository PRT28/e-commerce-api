package com.prt.storeapi.controller;

import com.prt.storeapi.entity.ApiResponse;
import com.prt.storeapi.entity.AuthRequest;
import com.prt.storeapi.entity.AuthResponse;
import com.prt.storeapi.entity.User;
import com.prt.storeapi.service.UserService;
import com.prt.storeapi.util.JwtUtil;
import com.prt.storeapi.util.RequestMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RequestMeta requestMeta;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> Auth(@RequestBody AuthRequest body) {
        ApiResponse response = new ApiResponse();
        User user = userService.getUserByEmail(body.getEmail());
        if (user == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setError("User Not Found");
            return ResponseEntity.status(response.getStatus()).body(response);
        } else if (!user.getPassword().equals(body.getPassword())) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError("Wrong Username or Password");
            return ResponseEntity.status(response.getStatus()).body(response);
        } else {
            String token = jwtUtil.generateToken(user);
            response.setStatus(HttpStatus.OK.value());
            AuthResponse data = new AuthResponse(token, user);
            response.setData(data);
            return ResponseEntity.status(response.getStatus()).body(response);
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> SignUp(@RequestBody User user) throws Exception {
        ApiResponse response = new ApiResponse();
        try {
            User temp = userService.getUserByEmail((user.getEmail()));
            if (temp == null) {
                userService.saveUser(user);
                response.setStatus(HttpStatus.OK.value());
                response.setData("User Saved Successfully");
            } else {
                response.setStatus(HttpStatus.BAD_GATEWAY.value());
                response.setError("User already exists");
            }
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse> edit(@RequestBody User user) {
        ApiResponse response = new ApiResponse();
        User temp = userService.getUserById(user.getId());
        if (temp == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setError("User does not exists");
        } else {
            userService.saveUser(user);
            response.setStatus(HttpStatus.OK.value());
            response.setData("User Updated Successfully");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> delete() {
        ApiResponse response = new ApiResponse();
        int id = requestMeta.getId();
        User temp = userService.getUserById(id);
        if (temp == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setError("User does not exists");
        } else {
            userService.deleteUserById(id);
            response.setStatus(HttpStatus.OK.value());
            response.setData("User Deleted Successfully");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> privateApi(@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
        ApiResponse response = new ApiResponse();
        jwtUtil.verify(auth);
        response.setData("Access Granted");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
