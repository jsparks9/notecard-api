package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUser {
    private long id;
    private String Username;
    private String fname;
    private String lname;

    public GetUser(User user) {
        this.id = user.getId();
        Username = user.getUsername();
        this.fname = user.getFname();
        this.lname = user.getLname();
    }
}
