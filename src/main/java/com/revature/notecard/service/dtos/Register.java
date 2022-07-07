package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Register {

    // Register endpoint corresponding parameters
    private String username;
    private String fname;
    private String lname;
    private String password;

    //User object maker to extract new user information as User Object
    public User extractUserInfo() {return new User(username, fname, lname, password);}
}
