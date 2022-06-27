package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewUserRequest {
    private String username;
    private String fname;
    private String lname;
    private String password;

    public User extractResource() {
        return new User(username, fname, lname, password);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password not blank='" + (password.length()>0) + '\'' +
                '}';
    }
}
