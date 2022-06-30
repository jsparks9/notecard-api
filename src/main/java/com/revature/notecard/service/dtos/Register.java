package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Register {

    private int role_id;
    private String username;
    private String fname;
    private String lname;
    private String password;

    public User extractUserInfo() {return new User(username, fname, lname, password);}
}
