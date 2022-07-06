package com.revature.notecard.tables;

import javax.persistence.*;

@Entity // tells ORN that this maps to a relational entity
@Table(name = "profile_pic")
public class ProfilePicURL {
    @Id
    @Column(name = "user_id", nullable = false)
    private long user_id;

    @Column(name="pic_url")
    private String pic_url;

    public String getPic_url() {
        return pic_url;
    }
}
