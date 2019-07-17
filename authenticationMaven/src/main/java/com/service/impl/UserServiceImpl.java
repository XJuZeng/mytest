package com.service.impl;

import com.mapper.UserMapper;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUser(String username) {

        return userMapper.findUser(username);
    }
}
