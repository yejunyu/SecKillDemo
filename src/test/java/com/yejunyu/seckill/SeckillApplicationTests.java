package com.yejunyu.seckill;

import com.yejunyu.seckill.redis.RedisService;
import com.yejunyu.seckill.redis.UserKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testIncr() {
        System.out.println(redisService.incr(UserKey.getByName, ""));
    }
}
