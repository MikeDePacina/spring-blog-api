package com.blogapi.blogapi.controller;

import com.blogapi.blogapi.dto.PostDto;
import com.blogapi.blogapi.dto.PostResponse;
import com.blogapi.blogapi.service.PostService;
import com.blogapi.blogapi.utils.APIConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all blog posts
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(name = "pageNo", defaultValue = APIConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = APIConstants.DEFAULT_PAGE_SIZE, required = false)int pageSize,
            @RequestParam(name = "sortBy", defaultValue = APIConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = APIConstants.DEFAULT_SORT_ORDER, required = false) String sortOrder
            ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortOrder);
    }

    //get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPost(id));
    }

    //update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        return new ResponseEntity<PostDto>(postService.updatePost(postDto, id),HttpStatus.OK);
    }

    //delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id")long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted sucessfully",HttpStatus.OK);
    }

}
