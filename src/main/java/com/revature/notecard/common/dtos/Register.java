package com.revature.notecard.common.dtos;

import com.revature.notecard.tables.users.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class Register {

    private String id;
    private int role_id;
    private String username;
    private String fname;
    private String lname;
    private String password;



    public User extractUserInfo() {
        return new User(username, fname, lname, password);
    }
}

