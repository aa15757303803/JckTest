package com.jck.dao;

import com.jck.entity.User;

import java.util.List;


public interface UserDao {

    /**
     * 根据id查询用户
     */
    List<User> getUsers();
}
