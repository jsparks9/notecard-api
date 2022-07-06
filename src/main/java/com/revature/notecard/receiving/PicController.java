package com.revature.notecard.receiving;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.repos.UserRepository;

public class PicController {
    private UserRepository userRepo;
    private ObjectMapper mapper = new ObjectMapper();
    private JwtConfig jwtConfig;
    private TokenService service;

    

}
