package com.blogapi.blogapi.service.impl;

import com.blogapi.blogapi.dto.PostDto;
import com.blogapi.blogapi.model.Post;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto){
        //convert postDto to post object/entity
        Post post =  new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //convert newPost to entity
        PostDto responsePost = new PostDto();
        responsePost.setId(newPost.getId());
        responsePost.setTitle(newPost.getTitle());
        responsePost.setDescription(newPost.getDescription());
        responsePost.setContent(newPost.getContent());

        return responsePost;
    }
}
