package com.ch.springboot.mapper;

import com.ch.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public User queryUserByname(String name);
}
