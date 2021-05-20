package com.ch.springboot;

import com.ch.springboot.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {
    @Autowired
    private  UserServiceImpl userService;
    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByname("admin"));
    }

}
