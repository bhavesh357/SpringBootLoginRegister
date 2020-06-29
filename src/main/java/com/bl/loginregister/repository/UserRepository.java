package com.bl.loginregister.repository;


import ch.qos.logback.core.boolex.EvaluationException;
import com.bl.loginregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.function.Supplier;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE email LIKE (concat('%',:email,'%')) AND password like (concat('%',:password,'%'))", nativeQuery = true)
    public List<User> searchByQuery(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM user WHERE email LIKE (concat('%',:email,'%'))", nativeQuery = true)
    public List<User> ifExist();
}