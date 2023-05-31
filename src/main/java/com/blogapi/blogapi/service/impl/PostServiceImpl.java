package com.blogapi.blogapi.service.impl;

import com.blogapi.blogapi.dto.PostDto;
import com.blogapi.blogapi.dto.PostResponse;
import com.blogapi.blogapi.exception.ResourceNotFoundException;
import com.blogapi.blogapi.model.Post;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortOrder){

//        pagination should also return the following(all of which pageable instance provides getters for):
//        - List<Post> content
//        - pageNo
//        - pageSize
//        - totalElements
//        - totalPages
//        - last

        //create sorting instance with specified order
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        //getting posts in a page
        List<Post> postsInPage = posts.getContent();

        List<PostDto> content = postsInPage.stream().map(post -> convertToDTO(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);

        //gets page number
        postResponse.setPageNo(posts.getNumber());

        //gets page size
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPost(long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return convertToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return convertToDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);

    }

    private PostDto convertToDTO(Post post){
        PostDto postDto = mapper.map(post,PostDto.class);
        //using ModelMapper library to convert obj now
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post convertToModel(PostDto postDto){

        Post post = mapper.map(postDto,Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;
    }
}
