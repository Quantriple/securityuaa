package com.uaa.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author TripleQ
 * @description
 * @date 2022/4/9 16:21:16
 * @VERSION 1.0
 **/

@Data
public class User implements UserDetails, Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


}
