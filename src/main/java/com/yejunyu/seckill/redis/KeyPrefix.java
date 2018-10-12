package com.yejunyu.seckill.redis;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
public interface KeyPrefix {
    int expireSeconds();

    String getPrefix();
}
