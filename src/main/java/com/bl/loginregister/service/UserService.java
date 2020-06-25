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

    public boolean validate(User user) {
        Optional<User> ogUser = repo.findById(user.getEmail());
        return user.getPassword().matches(ogUser.get().getPassword()) && user.getEmail().matches(ogUser.get().getEmail());
    }

    public String filter(User user) {
        return "";
    }

    public boolean validateRegister(UserDAO user) {
        Optional<User> ogUser = repo.findById(user.getEmail());
        return user.getPassword().matches(ogUser.get().getPassword()) && user.getEmail().matches(ogUser.get().getEmail());
    }

    public String filter(UserDAO user) {
        return "";
    }
}
