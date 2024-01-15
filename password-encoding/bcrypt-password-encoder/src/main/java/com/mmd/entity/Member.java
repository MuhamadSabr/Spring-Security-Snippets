package com.mmd.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "user_id")
    private String username;

    @Column(name = "active")
    private Integer active;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "pw")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "role") }
    )
    private List<Role> roles;

    public Member() {roles = new ArrayList<>();}

    public Member(String username, Integer active, LocalDateTime dateCreated, String password, List<Role> roles) {
        this();
        this.username = username;
        this.active = active;
        this.dateCreated = dateCreated;
        this.password = password;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", active=" + active +
                ", dateCreated=" + dateCreated +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
