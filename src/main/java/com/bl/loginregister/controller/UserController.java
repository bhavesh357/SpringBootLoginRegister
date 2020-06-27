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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
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
     * @return
     */
    @PostMapping("/login")
    public User printLogin(@RequestBody User user, HttpServletResponse response) throws IOException {
        User validate = service.validate(user);
        if (validate!=null){
            return validate;
        }
        response.sendError(-1);
        return null;
    }

    /**
     * post mapping for register
     * @param user
     * @param response
     * @return
     */
    @PostMapping("/register")
    public User printRegister(@RequestBody UserDAO user, HttpServletResponse response) throws IOException {
        User validate = service.validateRegister(user);
        if (validate!=null){
            return validate;
        }
        response.sendError(-1);
        return null;
    }
}
