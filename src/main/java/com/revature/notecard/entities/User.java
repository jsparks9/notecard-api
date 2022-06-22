package com.revature.notecard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;


    private int role_id;
    private String username;
    private String fname;
    private String lname;

    @Column(name="creationdate", columnDefinition = "varchar(10) default current_date")
    private String creatonDate;

    @Column(name="creationtime", columnDefinition = "varchar(18) default current_time")
    private String creationTime;
}
