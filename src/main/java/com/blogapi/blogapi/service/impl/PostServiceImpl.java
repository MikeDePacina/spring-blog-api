package com.blogapi.blogapi.service.impl;

import com.blogapi.blogapi.dto.PostDto;
import com.blogapi.blogapi.model.Post;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto){
        //convert postDto to post object/entity
        Post post = convertToModel(postDto);

        Post newPost = postRepository.save(post);

        //convert newPost to entity
        PostDto responsePost = convertToDTO(newPost);

        return responsePost;
    }

    @Override
    public List<PostDto> getAllPosts(){
        List<Post> posts = postRepository.findAll();
//        List<PostDto> postDtos = new ArrayList<>();
//        for(Post post: posts){
//            postDtos.add(convertToDTO(post));
//        }
        return posts.stream().map(post -> convertToDTO(post)).collect(Collectors.toList());

    }

    private PostDto convertToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post convertToModel(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
