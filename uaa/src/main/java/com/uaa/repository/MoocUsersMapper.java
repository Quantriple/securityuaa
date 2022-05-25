package com.uaa.repository;

import com.uaa.domain.MoocUsers;
import org.apache.ibatis.annotations.Param;

public interface MoocUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoocUsers record);

    int insertSelective(MoocUsers record);

    MoocUsers selectByPrimaryKey(Long id);

    MoocUsers selectByName(@Param("name") String name);

    MoocUsers selectByUsername(@Param("username") String username);

    int updateByPrimaryKeySelective(MoocUsers record);

    int updateByPrimaryKey(MoocUsers record);
}