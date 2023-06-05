package com.blogapi.blogapi.service;

import com.blogapi.blogapi.dto.CommentDto;


import java.util.List;

public interface CommentService {
    CommentDto postComment(long postID, CommentDto commentDto);

    List<CommentDto> getCommentsOfPost(long postID);

    CommentDto getCommentByID(long postID,long commentID);

    CommentDto updateCommentByID(long postID,long commentID, CommentDto commentDto);

    void deleteComment(long postID,long commentID);


}
