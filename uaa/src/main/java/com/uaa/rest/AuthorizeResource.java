package com.uaa.rest;


import com.uaa.domain.Auth;
import com.uaa.domain.dto.LoginDto;
import com.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class AuthorizeResource {


    private final UserService userService;

    @PostMapping("/token")
    public Auth login(@RequestBody LoginDto loginDto) throws Exception {

        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }

}
