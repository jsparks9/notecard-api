package com.revature.notecard;

import com.revature.notecard.service.auth.TokenService;
import com.revature.notecard.service.dtos.Principal;


public class testApp {
//    private static TokenService service = new TokenService();
//
//    public static void main(String[] args) {
//        Principal subject = new Principal(1L, "ADMIN");
//        String token = service.generateToken(subject);
//        System.out.println(token);
//        mkln(55);
//        Principal decoded = service.extractTokenDetails(token);
//        System.out.println(decoded);
//        mkln(55);
//        long now = System.currentTimeMillis();
//        long then = now + jwtConfig.getExpiration();
//    }

    private static void mkln(int i) {
        System.out.println(new String(new char[i]).replace("\0", "*"));
    }
}
