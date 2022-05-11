package com.uaa.security.userdetails;

import com.uaa.domain.MoocUsers;
import com.uaa.repository.MoocUsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

/**
 * @author TripleQ
 * @description 无缝进行密码升级
 * @date 2022/5/6 08:25:31
 * @VERSION 1.0
 **/
@Service
@RequiredArgsConstructor
public class UserDetailsPasswordServiceImpl implements UserDetailsPasswordService {
    private final MoocUsersMapper moocUsersMapper;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        MoocUsers details=moocUsersMapper.selectByName(userDetails.getUsername());
        if(details==null){
           return  userDetails;
        }
        details.setPasswordHash(newPassword);
        moocUsersMapper.updateByPrimaryKey(details);
        return details;
    }
}
