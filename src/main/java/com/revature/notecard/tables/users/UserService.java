package com.revature.notecard.tables.users;

import com.revature.notecard.common.dtos.Register;
import com.revature.notecard.common.dtos.UserCreationResponse;
import com.revature.notecard.common.utils.exceptions.ResourcePersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.UUID;
import static com.revature.notecard.service.Encrypt.encrypt;

@Service
@Validated
@Transactional
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Validated
    public UserCreationResponse createUser(@Valid Register regRequestInfo) {
        User newUser = regRequestInfo.extractUserInfo();

        if( userRepo.existsByUsername(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that username!");
        }

        if (userRepo.existsByUsername(newUser.getUsername())) {
            throw new ResourcePersistenceException("There is already a user with that email!");
        }

        newUser.setId(UUID.randomUUID().toString());
        newUser.setRole_id(1);
        newUser.setPassword(encrypt(newUser.getPassword()));
        userRepo.save(newUser);

        return new UserCreationResponse(newUser.getId());
    }
}
