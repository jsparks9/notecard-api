package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private String id;
    private String username;

    public LoginResponse(User user) {
        this.id = String.valueOf(user.getId());
        this.username = user.getUsername();
    }

}
