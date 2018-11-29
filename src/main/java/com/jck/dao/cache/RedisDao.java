package com.jck.dao.cache;

import com.jck.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {

    private JedisPool jedisPool;

    private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public User getUser(long userId) {
        try {
            Jedis jedis = jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis获取User用户错误!:"+e.getMessage());
        }
        return null;
    }

    public String putUser(User user) {
        return null;
    }
}
