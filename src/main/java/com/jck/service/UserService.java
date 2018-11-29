package com.jck.service;

import com.jck.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById();
}
