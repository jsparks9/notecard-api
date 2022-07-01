package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllUsersResponse {
    private long id;
    private String role;
    private String fname;
    private String lname;
    private String username;

    public GetAllUsersResponse(User user) {
        this.id = user.getId();
        this.role = "" + user.getRole();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.username = user.getUsername();
    }
}
