package com.blogapi.blogapi.service;

import com.blogapi.blogapi.dto.LoginDto;
import com.blogapi.blogapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);


}
