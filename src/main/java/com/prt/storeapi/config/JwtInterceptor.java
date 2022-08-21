package com.prt.storeapi.config;

import com.prt.storeapi.util.JwtUtil;
import com.prt.storeapi.util.RequestMeta;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    private RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta){
        this.requestMeta = requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("authorization");
        if( !(request.getRequestURI().contains("auth") || request.getRequestURI().contains("signup"))){
            Claims claims = jwtUtil.verify(auth);
            requestMeta.setId(Integer.parseInt(claims.get("id").toString()));
            requestMeta.setName(claims.get("name").toString());
            requestMeta.setEmail(claims.get("email").toString());
        }
        return super.preHandle(request, response, handler);
    }

}
