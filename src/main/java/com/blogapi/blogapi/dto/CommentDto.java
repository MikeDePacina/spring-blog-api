package com.blogapi.blogapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotBlank(message="Name can't be blank")
    private String name;
    @NotBlank(message = "Email can't be blank")
    @Email
    private String email;
    @NotBlank(message = "Can't post blank comment")
    @Size(min = 2, message = "Message should be at least 2 characters long")
    private String body;
}
