package com.baeldung.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ROLES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleID;

    @NotNull(message = "Role name cannot be null!")
    @Size(min = 4, max = 16, message = "Invalid role name length!")
    private String roleName;

    private Long userId;

    public Role(String roleName, Long userId) {
        this.roleName = roleName;
        this.userId = userId;
    }

    public Role() {}

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}