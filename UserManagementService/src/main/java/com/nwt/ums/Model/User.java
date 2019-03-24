package com.nwt.ums.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

// ovo za sad ne djeluje na bazu
@Entity
@Table(name = "USERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//
public class User {

    @Id
    //@Column(name="USER_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userID;


    @Column(name="FIRST_NAME", nullable = false)
    String firstName;

    @Column(name="LAST_NAME", nullable = false)
    String lastName;

    @Column(name="USERNAME", nullable = false)
    String username;

    @Column(name="PASSWORD", nullable = false)
    String password;


    public User(Long id, String firstName, String lastName, String index, String username, String password) {
        this.userID = id;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.index = index;
        this.username = username;
        this.password = password;
//        this.department = departmentId;
    }

    public User() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long id) {
        this.userID = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

