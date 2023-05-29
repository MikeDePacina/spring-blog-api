package com.blogapi.blogapi.service;

import com.blogapi.blogapi.dto.PostDto;
import com.blogapi.blogapi.dto.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortOrder);

    PostDto getPost(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);

}
