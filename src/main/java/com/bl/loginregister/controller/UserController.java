package com.bl.loginregister.controller;


import com.bl.loginregister.model.User;
import com.bl.loginregister.model.UserDAO;
import com.bl.loginregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public User printLogin(@Valid @RequestBody UserDAO user, HttpServletResponse response) throws IOException {
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
    public User printRegister(@Valid @RequestBody UserDAO user, HttpServletResponse response) throws IOException {
        User user1 = new User();
        User validate = service.validateRegister(user);
        if (validate!=null){
            return validate;
        }
        response.sendError(-1);
        return user1;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
