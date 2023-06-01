package com.blogapi.blogapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//@Data lombok annotation creates getters and setters, toString, equals
@Getter
@Setter
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

    @Column(name = "title",nullable = false)//if you don't specify name default will be the field
    private String title;

    @Column(nullable = false )
    private String description;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)//orphan removal deletes child entities in database when parent is removed
    private Set<Comment> comments = new HashSet<>();
}
