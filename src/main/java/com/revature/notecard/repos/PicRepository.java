package com.revature.notecard.repos;

import com.revature.notecard.tables.ProfilePicURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicRepository extends JpaRepository<ProfilePicURL, Long> {
}
