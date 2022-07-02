package com.revature.notecard.service.dtos;

import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;

}