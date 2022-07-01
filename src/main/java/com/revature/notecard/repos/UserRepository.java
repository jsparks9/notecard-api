package com.revature.notecard.repos;

import com.revature.notecard.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query
    Optional<User> getByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    @Query
    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
