package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllUsersResponse {
    private String role_id;
    private String fname;
    private String lname;
    private String username;

    public GetAllUsersResponse(User user) {
        this.role_id = ""+user.getRole();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.username = user.getUsername();
    }
}

