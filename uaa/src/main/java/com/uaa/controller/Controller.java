package com.uaa.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/greeting")
    public String hello(){
        return  "hello";
    }


    @PostMapping("/greeting")
    public String sayHello(@RequestParam String name){
        return  "hello"+name;
    }
}
