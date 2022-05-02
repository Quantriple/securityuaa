package com.uaa.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MoocRoles implements GrantedAuthority {
    private Long id;
    private String roleName;
    private String displayName;
    private Boolean builtIn;


    @Override
    public String getAuthority() {
        return roleName;
    }
}