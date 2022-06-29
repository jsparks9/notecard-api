package com.revature.notecard.receiving;

import com.revature.notecard.repos.UserRepository;
import com.revature.notecard.service.dtos.ChangeRoleRequest;
import com.revature.notecard.tables.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminview")
public class AuthController {
    private final UserRepository userRepo;

    @Autowired
    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "/setrole")
    public void fctname(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody ChangeRoleRequest req) {
        System.out.println(req); // works
        // does the username exist?
        // is it a valid username?


        User.Role intendedRole = null;
        for (User.Role role : User.Role.values()) {
            if (role.toString().equalsIgnoreCase(req.getRole())) intendedRole = role;
        }
        // was the intendedRole even set?
        if (intendedRole == null) throw new RuntimeException(); // TODO : custom exception
        // but what about te user id?

        User user = userRepo.getByUsernameIgnoreCase(req.getUsername()).orElseThrow(RuntimeException::new); // is this an Optional<User> ???
        System.out.println("Here's the user before " + user.getRole());
//        updateRole(long userId, String role
        userRepo.updateRole(""+user.getId(), intendedRole.toString());

        User userInDb = userRepo.findById(user.getId()).orElseThrow(RuntimeException::new);
        System.out.println("Here's the DB entry now " + userInDb.getRole());





        //set their role
        // done ?
    }
}
