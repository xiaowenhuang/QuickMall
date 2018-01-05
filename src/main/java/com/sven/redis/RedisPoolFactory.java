package com.sven.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by sven on 2018/1/6.
 */
@Component
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;
    @Bean
    public JedisPool jedisFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
        JedisPool jp = new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout()*1000
                ,redisConfig.getPassword(),0);
        return jp;
    }
}
