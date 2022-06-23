package com.revature.notecard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
// For JPA (annotations)

@Entity // tells ORN that this maps to a relational entity
@Table(name = "users")
public class User implements Comparable<User>{ // represents a record in the users table

    @Id // implies not null
    @Column(columnDefinition = "varchar(36) unique")
    private String id;

    @Column(columnDefinition = "int not null default 1")
    private int role_id;

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

    public User() {}

    public User(String id, int role_id, String username, String fname, String lname, String password) {
        this.id = id;
        this.role_id = role_id;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }

    public User(String id, int role_id, String username, String fname, String lname, String password, String creatonDate, String creationTime) {
        this(id, role_id, username, fname, lname, password);
        this.id = id;
        this.role_id = role_id;
        this.creatonDate = creatonDate;
        this.creationTime = creationTime;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getRole_id() { return role_id; }
    public void setRole_id(int role_id) { this.role_id = role_id; }
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
        return role_id == user.role_id && id.equals(user.id) && username.equals(user.username) && fname.equals(user.fname) && lname.equals(user.lname) && password.equals(user.password) && Objects.equals(creatonDate, user.creatonDate) && Objects.equals(creationTime, user.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role_id, username, fname, lname, password, creatonDate, creationTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", role_id=" + role_id +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", creatonDate='" + creatonDate + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
