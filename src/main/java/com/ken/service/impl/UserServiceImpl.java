package com.ken.service.impl;

import com.ken.entity.User;
import com.ken.mapper.UserMapper;
import com.ken.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yhq
 * @date 2019/3/21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveUser(User user) {
        userMapper.saveUser(user);
        return user;
    }

}
