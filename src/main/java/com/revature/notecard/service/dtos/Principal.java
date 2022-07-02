package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Principal {

    private long authUserId;
    private String authUserRole;

    private String authUsername;

    public Principal(User user) {
        this.authUserId = user.getId();
        this.authUserRole = user.getRole().toString();
    }

    public Principal(long authUserId, String authUserRole) {
        this.authUserId = authUserId;
        this.authUserRole = authUserRole;
    }

    public Principal (LoginResponse loginResponse) {
        this.authUserId = Long.parseLong(loginResponse.getId());
        this.authUsername = loginResponse.getUsername();
        this.authUserRole = loginResponse.getRole();
    }
}
