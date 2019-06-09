package com.baeldung.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @NotNull (message = "Username cannot be null!")
    @Size(min=5, max=30, message = "Username must have at lest 5 characters!")
    private String username;

    @NotNull (message = "Password cannot be null!")
    @Size(min=5, message = "Password must have at least 5 characters!")
    private String password;

    @NotNull (message = "First name cannot be null!")
    @Size(min=2, max=30, message = "First name must be between 2 and 30 characters long!")
    private String firstName;

    @NotNull (message = "Last name cannot be null!")
    @Size(min=2, max=30, message = "Last name must be between 2 and 30 characters long!")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;

    @NotNull (message = "Email cannot be null!")
  //  @Email (message = "Email should be valid!")
    private String email;

    private double longitude;

    private double latitude;

    private String confirmToken;

    private String reactivateToken;

    private String passwordToken;

    private Boolean enabled;

    public User(){}

    public User(@NotNull(message = "Username cannot be null!") @Size(min = 5, max = 30, message = "Username must have at lest 5 characters!") String username, @NotNull(message = "Password cannot be null!") @Size(min = 5, message = "Password must have at least 5 characters!") String password, @NotNull(message = "First name cannot be null!") @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters long!") String firstName, @NotNull(message = "Last name cannot be null!") @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters long!") String lastName, Role role, @NotNull(message = "Email cannot be null!") @Email(message = "Email should be valid!") String email, double longitude, double latitude, String confirmToken, String reactivateToken, String passwordToken, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
        this.confirmToken = confirmToken;
        this.reactivateToken = reactivateToken;
        this.passwordToken = passwordToken;
        this.enabled = enabled;
    }

    public User(@NotNull(message = "Username cannot be null!") @Size(min = 5, max = 30, message = "Username must have at lest 5 characters!") String username, @NotNull(message = "Password cannot be null!") @Size(min = 5, message = "Password must have at least 5 characters!") String password, @NotNull(message = "First name cannot be null!") @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters long!") String firstName, @NotNull(message = "Last name cannot be null!") @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters long!") String lastName, @NotNull(message = "Email cannot be null!") @Email(message = "Email should be valid!") String email, double longitude, double latitude, String confirmToken, String reactivateToken, String passwordToken, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
        this.confirmToken = confirmToken;
        this.reactivateToken = reactivateToken;
        this.passwordToken = passwordToken;
        this.enabled = enabled;
    }

    public User(String username, String password, String firstName, String lastName, double longitude, double latitude, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.role = role;
    }

    public User(User user) {
        this.role = user.role;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.username = user.username;
        this.password = user.password;
        this.longitude = user.longitude;
        this.latitude = user.latitude;
        this.passwordToken = user.passwordToken;
        this.confirmToken = user.confirmToken;
        this.reactivateToken = user.reactivateToken;
        this.enabled = user.enabled;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getConfirmToken() {
        return confirmToken;
    }

    public void setConfirmToken(String confirmToken) {
        this.confirmToken = confirmToken;
    }

    public String getReactivateToken() {
        return reactivateToken;
    }

    public void setReactivateToken(String reactivateToke) {
        this.reactivateToken = reactivateToke;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}