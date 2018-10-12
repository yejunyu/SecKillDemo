package com.yejunyu.seckill.controller;

import com.yejunyu.seckill.domain.User;
import com.yejunyu.seckill.redis.RedisService;
import com.yejunyu.seckill.redis.UserKey;
import com.yejunyu.seckill.result.Result;
import com.yejunyu.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/db/get/{id}")
    public Result<User> getUser(@PathVariable Integer id) {
        return Result.success(userService.getUser(id));
    }

    @GetMapping("redis/get/{key}")
    public Result<User> getRedisValue(@PathVariable String key) {
        return Result.success(redisService.get(UserKey.getById, key, User.class));
    }

    @GetMapping("redis/set")
    public Result<Boolean> setRedisValue() {
        User user = userService.getUser(1);
        return Result.success(redisService.set(UserKey.getById, "" + 1, user));
    }
}
