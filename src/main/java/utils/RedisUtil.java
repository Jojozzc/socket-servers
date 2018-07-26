package utils;

import redis.clients.jedis.JedisPool;

public class RedisUtil {
    private JedisPool jedisPool;

    public static RedisUtil getInstance() {
        return instance;
    }

    private static RedisUtil instance = new RedisUtil();
    private RedisUtil(){
        jedisPool = new JedisPool("localhost", 6379);
        System.out.println("redis pool ok");
    }
    public JedisPool getJedisPool(){
        return jedisPool;
    }

}
