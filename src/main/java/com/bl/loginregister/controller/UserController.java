package com.bl.loginregister.controller;


import com.bl.loginregister.model.User;
import com.bl.loginregister.model.UserDAO;
import com.bl.loginregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    UserService service;

    /**
     * get mapping for login
     * @return
     */
    @RequestMapping("/login")
    public String printLogin(){
        return "login";
    }

    /**
     * get mapping for register
     * @return
     */
    @RequestMapping("/register")
    public String printRegister(){
        return "register";
    }

    /**
     * post mapping for login
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String printLogin(@RequestBody User user, HttpServletRequest request){
        request.setAttribute("error",service.filter(user));
        return service.validate(user) ? "welcome": "login";
    }

    /**
     * post mapping for register
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/register")
    public String printRegister(@RequestBody UserDAO user, HttpServletRequest request){
        request.setAttribute("error",service.filter(user));
        return service.validateRegister(user) ? "login": "register";
    }
}
