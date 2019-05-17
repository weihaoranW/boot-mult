package com.moefor.demo.springboot_jwt.controller;

import com.moefor.demo.springboot_jwt.entity.User;
import com.moefor.demo.springboot_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signup(
            @RequestParam("username")
            String username,
            @RequestParam("password")
            String password
    ){
        return userService.add(username, password);
    }

}
