package com.revature.notecard.repos;

import com.revature.notecard.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query
    Optional<User> getByUsernameIgnoreCase(String username);

    @Query
    boolean existsByUsernameIgnoreCase(String username);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update users set role = :role where user_id = :userId ;")
    void updateRole(String userId, String role);
    void updateRole(long userId, String role);

}
