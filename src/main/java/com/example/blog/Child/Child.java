package com.example.blog.Child;

import com.example.blog.User.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name="children")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="description")
    private String description;



    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;




    @CreationTimestamp
    @Column(name="created_At", updatable = false)
    private Date created_At;

    @UpdateTimestamp
    @Column(name="updated_At")
    private Date updated_At;


    public Child() {
    }

    public Child(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Child(long id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Long getId(){return id;}



}