package com.uaa.domain;

import lombok.Data;

@Data
public class MoocUsers {
    private Long id;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private String email;

    private Boolean enabled;

    private String mfaKey;

    private String mobile;

    private String name;

    private String passwordHash;

    private String username;

    private Boolean usingMfa;


}