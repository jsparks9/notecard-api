package com.revature.notecard.service.token;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

//    public UserResponsePayload authenticateUserCredentials(@Valid AuthRequest authRequest) {
//        return userRepo.findUserByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword())
//                .map(UserResponsePayload::new)
//                .orElseThrow(AuthenticationException::new);
//    }
}
