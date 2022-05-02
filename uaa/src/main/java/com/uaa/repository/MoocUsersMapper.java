package com.uaa.repository;

import com.uaa.domain.MoocUsers;

public interface MoocUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoocUsers record);

    int insertSelective(MoocUsers record);

    MoocUsers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoocUsers record);

    int updateByPrimaryKey(MoocUsers record);
}