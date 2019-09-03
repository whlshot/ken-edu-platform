package com.ken.controller;

import com.ken.entity.User;
import com.ken.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yhq
 * @date 2019/3/26
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getUser")
    public User getUser(Integer userId) {
        return userService.getUserById(userId);
    }
}
