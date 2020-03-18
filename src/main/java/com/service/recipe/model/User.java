package com.service.recipe.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Column(unique = true)
    private String name;
    private String password;

    @Column(unique = true)
    private String email;
    private Date created;

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public User() {
    }

    public User(String name, String password, String email, Date created, Integer id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.created = created;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
