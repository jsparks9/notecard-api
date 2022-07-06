package com.revature.notecard.service.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrincipalWithToken {

    private long authUserId;
    private String authUserRole;
    private String authUsername;
    private String token;


    public PrincipalWithToken(Principal prin, String token) {
        this.authUserId = prin.getAuthUserId();
        this.authUserRole = prin.getAuthUserRole();
        this.authUsername = prin.getAuthUsername();
        this.token = token;
    }
}
