package com.uaa.domain.dto;

import com.uaa.annotation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author TripleQ
 * @description
 * @date 2022/4/9 16:23:58
 * @VERSION 1.0
 **/
@Data
public class UserDto {
    @NotNull
    @NotBlank
    @Size(min = 4,max = 8,message = "用户名的长度在4-8个字之间")
    private String username;
    @ValidPassword
    private String password;
    private String email;
    private String name;
}
