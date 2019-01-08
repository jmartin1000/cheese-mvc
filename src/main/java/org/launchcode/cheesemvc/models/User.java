package org.launchcode.cheesemvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {
    // old way
    // public static int nextId = 1;
    //private int userId;

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=5, max = 15)
    private String username;

    /* this one does a little more validation, but it doesn't allow user to skip this field
    @Email(regexp=".+@.+\\..+", message="Please provide a valid email address")
    */
    // this one has same criteria as client side validation
    @Email
    private String email;

    @NotNull
    @Size(min=6)
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    private LocalDate date;

    public User() {
        date = LocalDate.now();
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private void checkPassword () {
        if (getPassword()!=null && getVerifyPassword()!=null) {
            if (!getPassword().equals(getVerifyPassword())) {
                setVerifyPassword(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
