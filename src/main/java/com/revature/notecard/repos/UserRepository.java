package com.revature.notecard.repos;

import com.revature.notecard.tables.ProfilePicURL;
import com.revature.notecard.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Query that converts the username to lowercase to ignore casing and checks the database
    // for the username and returns the user if found
    @Query
    Optional<User> getByUsernameIgnoreCase(String username);

    // Query that checks if a user exists by username and returns true/false
    @Query
    boolean existsByUsernameIgnoreCase(String username);

    // Query that converts the username to lowercase to ignore casing and checks the database
    // to find a matching username/password pair and returns the user if found
    @Query
    Optional<User> findUserByUsernameIgnoreCaseAndPassword(String username, String password);


    // Custom native query that updates user roles using a user ID and an expected role
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update users set role = :role where user_id = :userId ;")
    void updateRole(long userId, String role);

    @Query(nativeQuery = true, value = "select * from profile_pic where user_id = :userId limit 1;")
    Optional<ProfilePicURL> findProfilePic(long userId);
}
