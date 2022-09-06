package com.uaa.rest;


import com.uaa.domain.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class Controller {

    private AtomicInteger ai=new AtomicInteger(1);

    @GetMapping("/greeting/")
    public String hello() throws InterruptedException {
        System.out.println("ai11"+ai.getAndIncrement());
       // Thread.sleep(TimeUnit.MINUTES.toMinutes(1L));
        return  "hello";
    }


    @PostMapping("/greeting")
    public String sayHello(@Valid  @RequestBody UserDto userDto){
        System.out.println(userDto);
        return  "hello"+userDto.toString();
    }
}
