package com.bl.loginregister.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class User {
    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    @Size(min=1)
    @Pattern(regexp = "[\\\\w\\\\d]{1,}[.\\-#!]?[\\\\w\\\\d]{1,}@[\\\\w\\\\d]{1,}.[a-z]{2,3}.?([a-z]{2})?")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min=1)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[A-Za-z0-9@#!$%^&*()_-]{8,})[A-Za-z0-9]+?[@#!$%^&*()_-][A-Za-z0-9]{1,}?$")
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
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
}
