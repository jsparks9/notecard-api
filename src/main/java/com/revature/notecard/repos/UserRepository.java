package com.revature.notecard.repos;

import com.revature.notecard.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameIgnoreCase(String username);
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByUsernameIgnoreCaseAndPassword(String username, String password);

}
