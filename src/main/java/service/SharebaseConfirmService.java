package service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.RedisUtil;


public class SharebaseConfirmService {
    RedisUtil redisUtil = RedisUtil.getInstance();
    public String confirmAndGetUserId(String token){
        if(token == null) return null;
        JedisPool pool = redisUtil.getJedisPool();
        Jedis jedis = pool.getResource();
        return jedis.get(token);
    }
}
