package com.revature.notecard.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ban {
    private String id;
    private String username;
} // The idea here is banning can occur by ID or username
