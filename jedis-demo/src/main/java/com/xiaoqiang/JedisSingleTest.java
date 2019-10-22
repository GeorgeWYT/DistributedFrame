package com.xiaoqiang;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 简单使用
 * @author George on 2019-10-22
 **/
public class JedisSingleTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMinIdle(20);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000, null);

        Jedis jedis;
        try {
            jedis = jedisPool.getResource();
            jedis.set("single", "xiaoqiang");
            System.out.println(jedis.get("single"));
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
