package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

    private long id;
    private String username;

    // Takes in a user and provides getters for the user ID and username
    public RegistrationResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
