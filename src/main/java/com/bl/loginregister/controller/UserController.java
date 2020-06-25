package com.bl.loginregister.controller;


import com.bl.loginregister.model.User;
import com.bl.loginregister.model.UserDAO;
import com.bl.loginregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping("/login")
    public String printLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String printRegister(){
        return "register";
    }

    @PostMapping("/login")
    public String printWelcome(@RequestBody User user, HttpServletRequest request){
        request.setAttribute("error",service.filter(user));
        String result = service.validate(user) ? "welcome": "login";
        return result;
    }

    @PostMapping("/register")
    public String printLogin(@RequestBody UserDAO user, HttpServletRequest request){
        boolean validate = service.validateRegister(user);
        String result = validate ? "login": "register";
        return result;
    }
}
