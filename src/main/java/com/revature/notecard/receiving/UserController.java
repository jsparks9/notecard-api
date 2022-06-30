package com.revature.notecard.receiving;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

//    @GetMapping(path="/all")
//    public ResponseEntity getAllUsers() {
//        List<User>
//        //GetAllUsersResponse
//
//        return null;
//    }



}
