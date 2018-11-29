package com.jck.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.jck.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {

    private JedisPool jedisPool;

    private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public User getUser(long userId) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "user:"+userId;
                // 并没有实现内部序列化操作
                // get->byte[] -> 反序列化 -> Object(User)
                byte[] bytes = jedis.get(key.getBytes());
                if (null != bytes) {
                    User user = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, user, schema);
                    // user被反序列化
                    return user;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis获取User对象错误!:"+e.getMessage(),e);
        }
        return null;
    }

    public String putUser(User user) {
        // set Object(User) -> 序列化 -> byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "user:"+user.getId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 缓存超时时间
                int timeout = 60 * 60;// 1小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error("Redis添加User对象错误!:"+e.getMessage(),e);
        }
        return null;
    }
}
