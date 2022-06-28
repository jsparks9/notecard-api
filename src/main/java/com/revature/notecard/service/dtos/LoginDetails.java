package com.revature.notecard.service.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDetails {
    private String username;
    private String password;
}
