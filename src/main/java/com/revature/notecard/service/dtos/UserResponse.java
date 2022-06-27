package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private long id;
    private String role;
    private String username;
    private String fname;
    private String lname;

    public UserResponse(User user) {
        this.id = user.getId();
        this.role = user.getRole().toString();
        this.username = user.getUsername();
        this.fname = user.getFname();
        this.lname = user.getLname();
    }
}
