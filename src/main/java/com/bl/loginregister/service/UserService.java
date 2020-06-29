package com.bl.loginregister.service;

import com.bl.loginregister.model.User;
import com.bl.loginregister.model.UserDAO;
import com.bl.loginregister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    /**
     * Method to validate if user exist in db
     * @param user
     * @return boolean
     */
    public User validate(UserDAO user) {
        try {
            List<User> users = repo.searchByQuery(user.getEmail(), user.getPassword());
            return users.get(0);
            //return repo.findAll().stream().filter(u -> u.getEmail().matches(user.getEmail()))
                //.filter(u ->u.getPassword().matches(user.getPassword())).findFirst().get();
        }catch (IndexOutOfBoundsException e){
            return null;
        }
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
    public User validateRegister(UserDAO user) {
        if(repo.ifExist(user.getEmail()).isEmpty()){
            User user1 = new User();
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            repo.save(user1);
            return user1;
        }
        return null;
    }

}
