package com.revature.notecard.tables.users;

import com.revature.notecard.tables.cards.Card;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
// For JPA (annotations)

@Entity // tells ORN that this maps to a relational entity
@Table(name = "users")
public class User implements Comparable<User>{ // represents a record in the users table

    @Id // implies not null
    @Column(columnDefinition = "varchar(36) unique")
    private String id;

    @Enumerated
    @Column(name = "role", columnDefinition = "VARCHAR not null default 'BASIC' ")
    private UserRole role;

    @Column(columnDefinition = "varchar(32) unique not null check(length(username)<32 and length(username)>14 and (username like '%revature.net' " +
            " or username like '%Revature.net'))")
    private String username;

    @Column(columnDefinition = "varchar(16) not null check(length(fname)<16)")
    private String fname;

    @Column(columnDefinition = "varchar(16) not null check(length(lname)<16)")
    private String lname;

    @Column(columnDefinition = "varchar(64) not null")
    private String password;

    @Column(name="creationdate", columnDefinition = "varchar(10) default current_date")
    private String creatonDate;

    @Column(name="creationtime", columnDefinition = "varchar(18) default current_time")
    private String creationTime;

    // Removed due to errors
//    @OneToMany(mappedBy="id")
//    private List<Card> cards;

    public User() { super(); }


    public User(String id, UserRole role, String username, String fname, String lname, String password) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }

    public User(String id, UserRole role, String username, String fname, String lname, String password, String creatonDate, String creationTime) {
        this(id, role, username, fname, lname, password);
        this.id = id;
        this.role = role;
        this.creatonDate = creatonDate;
        this.creationTime = creationTime;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }
    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCreatonDate() { return creatonDate; }
    public void setCreatonDate(String creatonDate) { this.creatonDate = creatonDate; }
    public String getCreationTime() { return creationTime; }
    public void setCreationTime(String creationTime) { this.creationTime = creationTime; }

    @Override
    public int compareTo(User o) {
        if (this == o) return 0;
        if (getId() != null) {
            return getId().compareTo(o.getId());
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return role == user.role && id.equals(user.id) && username.equals(user.username) && fname.equals(user.fname) && lname.equals(user.lname) && password.equals(user.password) && Objects.equals(creatonDate, user.creatonDate) && Objects.equals(creationTime, user.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, username, fname, lname, password, creatonDate, creationTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", role_id=" + role+
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", creatonDate='" + creatonDate + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }

    public enum UserRole {
        SYSTEM, BASIC, ADMIN, BANNED;

    }
}
