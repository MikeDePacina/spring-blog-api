package com.blogapi.blogapi.repository;

import com.blogapi.blogapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


//Don't need @Repository annotation becuase JpaRepository already does
public interface PostRepository extends JpaRepository<Post, Long> {

}
