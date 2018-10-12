package com.yejunyu.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jp = null;
        try {
            jp = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jp.get(realKey);
            T t = strToBean(str, clazz);
            return t;
        } finally {
            returnPool(jp);
        }
    }

    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jp = null;
        try {
            jp = jedisPool.getResource();
            String str = beanToStr(value);
            if (StringUtils.isEmpty(str) || StringUtils.isEmpty(key)) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            if (prefix.expireSeconds() > 0) {
                jp.setex(realKey, prefix.expireSeconds(), str);
            } else {
                jp.set(realKey, str);
            }
            return true;
        } finally {
            returnPool(jp);
        }
    }

    public boolean exist(KeyPrefix prefix, String key) {
        Jedis jp = null;
        try {
            jp = jedisPool.getResource();
            if (StringUtils.isEmpty(key)) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            return jp.exists(realKey);
        } finally {
            returnPool(jp);
        }
    }

    public Long incr(KeyPrefix prefix, String key) {
        Jedis jp = null;
        try {
            jp = jedisPool.getResource();
            if (StringUtils.isEmpty(key)) {
                return 0L;
            }
            String realKey = prefix.getPrefix() + key;
            return jp.incr(realKey);
        } finally {
            returnPool(jp);
        }
    }

    public Long decr(KeyPrefix prefix, String key) {
        Jedis jp = null;
        try {
            jp = jedisPool.getResource();
            if (StringUtils.isEmpty(key)) {
                return 0L;
            }
            String realKey = prefix.getPrefix() + key;
            return jp.decr(realKey);
        } finally {
            returnPool(jp);
        }
    }

    private <T> String beanToStr(T value) {
        if (null == value) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T strToBean(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.parseObject(str, clazz);
        }
    }

    private void returnPool(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
