package com.revature.notecard.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Register {
    private String username;
    private String fname;
    private String lname;
    private String password;
}
