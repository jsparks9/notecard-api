package com.revature.notecard.tables;

import com.revature.notecard.tables.Deck;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
// For JPA (annotations)

@Entity // tells ORN that this maps to a relational entity
@Table(name = "users")
public class User { // represents a record in the users table

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private long user_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(columnDefinition = "varchar(32) unique not null check(length(username)<32 and length(username)>14 and (username like '%revature.net' " +
            " or username like '%Revature.net'))")
    private String username;

    @Column(columnDefinition = "varchar(16) not null check(length(fname)<16)")
    private String fname;

    @Column(columnDefinition = "varchar(16) not null check(length(lname)<16)")
    private String lname;

    @Column(columnDefinition = "varchar(64) not null")
    private String password;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "creation", insertable = false,
            updatable  = false,
            nullable   = false,  columnDefinition = "timestamp not null default current_timestamp")
    private String creation;

    @OneToMany(mappedBy = "creator")
    private List<Deck> createdDecks;

    public User() {
        super();
        this.createdDecks = new ArrayList<>();
    }

    public User(long user_id, String username, String fname, String lname,  String password, List<Deck> createdDecks) {
        this(fname, lname, username, password, createdDecks);
        this.user_id = user_id;
    }

    public User(String username, String firstName, String lastName,  String password) {
        this();
        this.fname = firstName;
        this.lname = lastName;
        this.username = username;
        this.password = password;
    }

    public User(String username, String firstName, String lastName,  Role role, String password) {
        this();
        this.fname = firstName;
        this.lname = lastName;
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public User(String username, String firstName, String lastName,  String password, List<Deck> createdDecks) {
        this(firstName, lastName, username, password);
        this.createdDecks = createdDecks;
    }

    public long       getId           () { return user_id      ; }
    public Role       getRole         () { return role         ; }
    public String     getUsername     () { return username     ; }
    public String     getFname        () { return fname        ; }
    public String     getLname        () { return lname        ; }
    public String     getPassword     () { return password     ; }
//    public String     getCreatonDate  () { return creationDate ; }
//    public String     getCreationTime () { return creationTime ; }
    public List<Deck> getCreatedDecks () { return createdDecks ; }

    public void setId           (long       id           ) { this.user_id      = id           ; }
    public void setRole         (Role       role         ) { this.role         = role         ; }
    public void setUsername     (String     username     ) { this.username     = username     ; }
    public void setFname        (String     fname        ) { this.fname        = fname        ; }
    public void setLname        (String     lname        ) { this.lname        = lname        ; }
    public void setPassword     (String     password     ) { this.password     = password     ; }
//    public void setCreationDate (String     creationDate ) { this.creationDate = creationDate ; }
//    public void setCreationTime (String     creationTime ) { this.creationTime = creationTime ; }
    public void setCreatedDecks (List<Deck> createdDecks ) { this.createdDecks = createdDecks ; }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", amount of createdDecks=" + createdDecks.size() +
                ", password='" + password + '\'' +
                '}';
    }

    public enum Role {
        ADMIN, BASIC, DEV, TESTER, BANNED;
    }
}
