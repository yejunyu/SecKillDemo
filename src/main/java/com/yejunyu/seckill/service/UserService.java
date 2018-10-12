package com.yejunyu.seckill.service;

import com.yejunyu.seckill.dao.UserMapper;
import com.yejunyu.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Integer id) {
        return userMapper.getUserById(id);
    }

}
