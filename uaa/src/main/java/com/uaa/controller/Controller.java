package com.uaa.controller;


import com.uaa.domain.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/greeting")
    public String hello(){
        return  "hello";
    }


    @PostMapping("/greeting")
    public String sayHello(@Valid  @RequestBody UserDto userDto){
        System.out.println(userDto);
        return  "hello"+userDto.toString();
    }
}
