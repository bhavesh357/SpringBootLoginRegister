package com.bl.loginregister.controller;


import com.bl.loginregister.model.User;
import com.bl.loginregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping("/login")
    public String printLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String printWelcome(@RequestBody User user){
        String result = service.validate(user) ? "welcome" : "login";
        return result;
    }
}
