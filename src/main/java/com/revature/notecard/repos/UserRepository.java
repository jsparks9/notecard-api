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

//    @Modifying
//    @Query(nativeQuery = true, value = "insert into card_deck values(:deckId, :cardId)") // JPQL/HQL
//    void addCard(long deckId, long cardId);

//    @Modifying
//    @Query(value="update User set role = :role")
//    void updateRole(User user, String role);
    @Transactional
    @Modifying  //update users set role= 'abcd' where user_id = 2; -- this works
    @Query(nativeQuery = true, value = "update users set role = :role where user_id = :userId ;")
    void updateRole(long userId, String role);

}
