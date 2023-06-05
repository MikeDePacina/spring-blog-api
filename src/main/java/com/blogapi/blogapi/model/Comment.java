package com.blogapi.blogapi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY) //tells hibernate to only fetch the related entities from the database when you use the relationship
    //a post can have multiple comments
    @JoinColumn(name = "postID", nullable = false) //foreign key
    private Post post;

}
