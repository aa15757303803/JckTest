package com.jck.dao;

import com.jck.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit  spring的配置文件
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    //注入dao实现类依赖
    @Resource
    private UserDao userDao;

    @Test
    public void getUserById() {
        int id = 1;
        List<User> someones = userDao.getUsers();
        for (User user: someones) {
            System.out.println(user.toString());
        }
    }
}