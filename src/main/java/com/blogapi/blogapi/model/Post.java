package com.blogapi.blogapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok annotation creates getters and setters
@AllArgsConstructor // creates a constructor with all the properties as parameters
@NoArgsConstructor//creates a no parameter constructor

@Entity//tells that the class is an entity in the db
@Table(
        name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id//specifies primary key
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description", nullable = false )
    private String description;

    @Column(name = "content", nullable = false)
    private String content;
}
