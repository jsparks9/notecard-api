package com.revature.notecard.service.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeRoleRequest {
    private String username;
    private String role;
}
