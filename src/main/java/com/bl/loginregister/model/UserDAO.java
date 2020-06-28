package com.bl.loginregister.model;


import javax.validation.constraints.*;
import java.util.Objects;

public class UserDAO {

    @NotNull
    @NotEmpty
    @Email
    @NotBlank
    @Size(min=1)
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=1)
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=1)
    private String repeatPassword;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDAO userDAO = (UserDAO) o;
        return Objects.equals(email, userDAO.email) &&
                Objects.equals(password, userDAO.password) &&
                Objects.equals(repeatPassword, userDAO.repeatPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, repeatPassword);
    }
}
