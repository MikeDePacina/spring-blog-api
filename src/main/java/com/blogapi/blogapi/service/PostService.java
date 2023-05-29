package com.blogapi.blogapi.service;

import com.blogapi.blogapi.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPost(long id);

    PostDto updatePost(PostDto postDto, long id);

}
