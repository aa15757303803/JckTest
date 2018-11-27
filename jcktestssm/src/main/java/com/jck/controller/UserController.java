package com.jck.controller;

import com.jck.entity.User;
import com.jck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/user")  // url:/模块/资源/{id}/细分
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "list"; // WEB-INF/jsp/"list".jsp
    }
}
