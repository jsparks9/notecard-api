package com.revature.notecard.common.dtos;

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
