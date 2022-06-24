package com.revature.notecard.tables.users;

import com.revature.notecard.tables.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findUserByUsername(String username);
    Optional<User> findUserByUsernameAndPassword(String username, String password);
//    List<User> findUserById(long user_id);
//    User findUserByUsername(String username);

}
