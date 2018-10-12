package com.yejunyu.seckill.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {
    private String host;
    private Integer port;
    private Integer timeout;
    private Integer poolMaxTotal;
    private Integer poolMaxIdle;
    private Integer poolMaxWait;

}
