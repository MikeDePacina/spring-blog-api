package com.blogapi.blogapi.controller;

import com.blogapi.blogapi.dto.CommentDto;
import com.blogapi.blogapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postID}/comments")
    public ResponseEntity<CommentDto> postComment(@PathVariable(name = "postID") long postID,
                                                  @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.postComment(postID,commentDto), HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postID}/comments")
    public List<CommentDto> getCommentsOfPost(@PathVariable(name = "postID") long postID){
        return commentService.getCommentsOfPost(postID);
    }

    @GetMapping("/posts/{postID}/comments/{commentID}")
    public ResponseEntity<CommentDto> getCommentByID(@PathVariable(name = "postID")long postID,
                                     @PathVariable(name = "commentID")long commentID){

        return new ResponseEntity<>(commentService.getCommentByID(postID,commentID), HttpStatus.OK);
    }

    @PutMapping("/posts/{postID}/comments/{commentID}")
    public ResponseEntity<CommentDto> updateCommentByID( @PathVariable(name = "postID")long postID,
                                                     @PathVariable(name = "commentID")long commentID,
                                                         @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.updateCommentByID(postID,commentID, commentDto), HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postID}/comments/{commentID}")
    public ResponseEntity<String> deleteCommentByID(@PathVariable(name = "postID")long postID,
                                                     @PathVariable(name = "commentID")long commentID){
        commentService.deleteComment(postID,commentID);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }


}
