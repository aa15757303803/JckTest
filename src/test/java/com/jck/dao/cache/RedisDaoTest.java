package com.jck.dao.cache;

import com.jck.dao.UserDao;
import com.jck.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private UserDao userDao;

    private long id = 1;

    @Test
    public void test() {
        User user = redisDao.getUser(1);
        if (null == user) {
            user = userDao.getUsers().get(0);
            if (null != user) {
                String result = redisDao.putUser(user);
                System.out.println("!!!!!!!Redis缓存中存储了一个用户对象:"+result);
                user = redisDao.getUser(1);
                System.out.println("!!!!!!!Redis缓存中输出了一个用户对象:"+user);
            }
        }
    }

}