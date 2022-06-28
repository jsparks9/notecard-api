package com.revature.notecard.service.auth;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.LoginDetails;
import com.revature.notecard.tables.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@Transactional
public class AuthService {

//    public UserResponsePayload authenticateUserCredentials(@Valid AuthRequest authRequest) {
//        return userRepo.findUserByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword())
//                .map(UserResponsePayload::new)
//                .orElseThrow(AuthenticationException::new);
//    }
}
