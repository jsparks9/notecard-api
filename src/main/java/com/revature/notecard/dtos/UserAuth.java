package com.revature.notecard.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserAuth {
    private String username;
    private String password;
    private String fname;
    private String lname;
}
