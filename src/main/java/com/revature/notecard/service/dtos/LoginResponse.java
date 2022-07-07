package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private String id;
    private String username;
    private String role;


    public LoginResponse(User user) {
        this.id = String.valueOf(user.getId());
        this.username = user.getUsername();
        this.role = user.getRole().toString();
    }

}
