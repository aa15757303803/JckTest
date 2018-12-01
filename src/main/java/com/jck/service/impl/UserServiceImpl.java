package com.jck.service.impl;

import com.jck.dao.UserDao;
import com.jck.dao.cache.RedisDao;
import com.jck.entity.User;
import com.jck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisDao redisDao;

    /**
     * 使用注解控制事务方法的优点
     * 1：开发团队达成一致约定，明确标注事务方法的编程风格
     * 2：保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务外部的方法
     * 3：不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    @Override
    @Transactional
    public List<User> getUsers() {
        List<User> users = userDao.getUsers();
        return users;
    }

    @Override
    @Transactional
    public void tran(User user) {
        userDao.insert(user);
        int i = 1/0;
        User uOne = new User();
        uOne.setId(1);
        uOne.setPassword("123");
        uOne.setUserName("hahaha");
        userDao.updateByPrimaryKeySelective(uOne);
    }

    @Override
    public User getUserById() {
        User user = redisDao.getUser(1);
        if (null == user) {
            user = userDao.getUsers().get(0);
            if (null == user) {
                return null;
            } else {
                redisDao.putUser(user);
            }
        }
        return user;
    }
}
