package com.jck.service.impl;

import com.jck.entity.User;
import com.jck.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class UserServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Test
    public void getUsers() {
        List<User> list = userService.getUsers();
        logger.info("list={}",list);
    }

    @Test
    public void getUser() {
        User user = userService.getUserById();
        logger.info("user={}",user);
    }

    @Test
    public void tran() {
        User user = new User();
        user.setId(2);
        user.setUserName("yesItIs");
        user.setPassword("123");
        userService.tran(user);
    }
}