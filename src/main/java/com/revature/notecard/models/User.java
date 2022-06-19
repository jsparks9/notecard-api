package com.revature.notecard.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private int id;
    private int role_id;
    private String username;
    private String fname;
    private String lname;
    private String password;
}
