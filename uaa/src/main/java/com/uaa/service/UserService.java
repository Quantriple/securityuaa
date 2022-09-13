package com.uaa.service;


import com.uaa.domain.Auth;
import com.uaa.domain.MoocUsers;
import com.uaa.repository.MoocUsersMapper;
import com.uaa.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MoocUsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public Auth login(String username, String password) throws AuthenticationException {
        MoocUsers moocUsers = usersMapper.selectByUsername(username);
        return Optional.of(moocUsers).filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new Auth(jwtUtil.createAccessToken(user), jwtUtil.createRefreshToken(user)))
                .orElseThrow(() -> new BadCredentialsException("用户名或密码错误"))
                ;

    }

}
