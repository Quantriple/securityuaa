package com.uaa.rest;


import com.uaa.domain.Auth;
import com.uaa.domain.dto.LoginDto;
import com.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class AuthorizeResource {


    private final UserService userService;

    @PostMapping("/token")
    public Auth login(@RequestBody LoginDto loginDto) throws Exception {

        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @PostMapping("/token/refresh")
    public Auth refreshToken(@RequestHeader(value = "Authorization") String authorization,
                             @RequestParam(value = "refreshToken") String refreshToken) throws Exception {
        String prefix = "Bearer ";
        String replace = authorization.replace(prefix, "");
       // return userService.login(loginDto.getUsername(), loginDto.getPassword());
        return null;
    }

}
