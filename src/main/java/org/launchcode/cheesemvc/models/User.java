package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class User {
    public static int nextId = 1;

    @NotNull
    @Size(min=5, max = 15)
    private String username;

    private String email;

    @NotNull
    @Size(min=6)
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    private LocalDate date;
    private int userId;

    public User() {
        userId = nextId;
        nextId++;
        date = LocalDate.now();
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;

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

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
