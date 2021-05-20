package com.ch.springboot.service.impl;

import com.ch.springboot.mapper.UserMapper;
import com.ch.springboot.model.User;
import com.ch.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//   调mapper层
    @Autowired
      private UserMapper userMapper;
    @Override
    public User queryUserByname(String name) {

        return userMapper.queryUserByname(name);
    }
}
