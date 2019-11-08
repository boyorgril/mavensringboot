package com.oldsix.test.service;

import com.oldsix.test.datasource.dto.User;
import com.oldsix.test.datasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Boolean addUser(User user){
        userMapper.insertSelective(user);
        return true;
    }

    public List<User> findAllUser(){
       List<User> userList = userMapper.selectByExample();
       return userList;
    }

    @Transactional
    public void updateUser(User user){
        userMapper.updateByPrimaryKey(user);
    }
}
