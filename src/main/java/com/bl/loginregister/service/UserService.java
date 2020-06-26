package com.bl.loginregister.service;

import com.bl.loginregister.model.User;
import com.bl.loginregister.model.UserDAO;
import com.bl.loginregister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    /**
     * Method to validate if user exist in db
     * @param user
     * @return boolean
     */
    public boolean validate(User user) {
        Optional<User> ogUser = repo.findById(user.getEmail());
        return user.getPassword().matches(ogUser.get().getPassword()) && user.getEmail().matches(ogUser.get().getEmail());
    }

    /**
     * Method to filter user
     * @param user
     * @return error message
     */
    public String filter(User user) {
        return "";
    }
    /**
     * Method to validate if user exist in db
     * @param user
     * @return boolean
     */
    public boolean validateRegister(UserDAO user) {
        Optional<User> ogUser = repo.findById(user.getEmail());
        return user.getPassword().matches(ogUser.get().getPassword()) && user.getEmail().matches(ogUser.get().getEmail());
    }

    /**
     * Method to filter and get error message
     * @param user
     * @return
     */
    public String filter(UserDAO user) {
        return "";
    }
}
