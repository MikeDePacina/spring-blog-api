package com.blogapi.blogapi.service.impl;

import com.blogapi.blogapi.dto.CommentDto;
import com.blogapi.blogapi.exception.BlogAPIException;
import com.blogapi.blogapi.exception.ResourceNotFoundException;
import com.blogapi.blogapi.model.Comment;
import com.blogapi.blogapi.model.Post;
import com.blogapi.blogapi.repository.CommentRepository;
import com.blogapi.blogapi.repository.PostRepository;
import com.blogapi.blogapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }



    @Override
    public CommentDto postComment(long postID, CommentDto commentDto) {
        Comment comment = convertToModel(commentDto);

        //get post with specified ID
        Post post = getPostByID(postID);
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        CommentDto commentResponse = convertToDTO(newComment);

        return commentResponse;
    }

    @Override
    public List<CommentDto> getCommentsOfPost(long postID) {
        //get comments of a post given its ID
        List<Comment> comments = commentRepository.findByPostId(postID);

        return comments.stream().map(comment -> convertToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentByID(long postID,long commentID) {
        Post post = getPostByID(postID);
        Comment comment = getCommentByID(commentID);
        if(comment.getPost().getId() != (post.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not exists");
        return convertToDTO(comment);
    }

    @Override
    public CommentDto updateCommentByID(long postID, long commentID, CommentDto commentDto) {
        Post post = getPostByID(postID);
        Comment comment = getCommentByID(commentID);
        if(comment.getPost().getId() != (post.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not exists");

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody((commentDto.getBody()));

        Comment updatedComment = commentRepository.save(comment);

        return convertToDTO(updatedComment);
    }

    @Override
    public void deleteComment(long postID, long commentID) {
        Post post = getPostByID(postID);
        Comment comment = getCommentByID(commentID);
        if(comment.getPost().getId() != (post.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not exists");

        commentRepository.delete(comment);
    }

    //using ModelMapper now
    private CommentDto convertToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Comment convertToModel(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return comment;
    }

    private Post getPostByID(long postID){
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post","id",postID));
        return post;
    }

    private Comment getCommentByID(long commentID){
        Comment comment = commentRepository.findById(commentID).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentID));
        return comment;
    }
}
