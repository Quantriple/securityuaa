package com.uaa.security.userdetails;

import com.uaa.repository.MoocUsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author TripleQ
 * @description
 * @date 2022/5/4 10:10:57
 * @VERSION 1.0
 **/
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MoocUsersMapper moocUsersMapper;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return null;
    }
}
