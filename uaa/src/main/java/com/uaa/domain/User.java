package com.uaa.domain;

import lombok.Data;

/**
 * @author TripleQ
 * @description
 * @date 2022/4/9 16:21:16
 * @VERSION 1.0
 **/

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String name;
}
