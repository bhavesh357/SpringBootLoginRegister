package com.bl.loginregister.model;


import javax.validation.constraints.*;
import java.util.Objects;

public class UserDAO {

    @NotEmpty
    @Pattern(regexp = "[\\w\\d]{1,}[.\\-#!]?[\\w\\d]{1,}@[\\w\\d]{1,}.[a-z]{2,3}.?([a-z]{2})?",message = "Enter Valid Email with 1 Capital 1 small 1 special character and 1 number")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[A-Za-z0-9@#!$%^&*()_]{8,})[A-Za-z0-9]+?[@#!$%^&*()_][A-Za-z0-9]{1,}?$",message = "Enter valid password with one number one capital 1 small character and a special character")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDAO userDAO = (UserDAO) o;
        return Objects.equals(email, userDAO.email) &&
                Objects.equals(password, userDAO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
