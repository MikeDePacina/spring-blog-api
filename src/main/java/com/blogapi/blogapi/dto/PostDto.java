package com.blogapi.blogapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    @NotBlank
    @Size(min = 2, message = "should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "description should have at least 10 characters")
    private String description;
    @NotBlank
    @Size(min = 2)
    private String content;
    private Set<CommentDto> comments;
}
