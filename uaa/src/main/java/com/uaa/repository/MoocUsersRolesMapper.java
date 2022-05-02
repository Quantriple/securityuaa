package com.uaa.repository;

import com.uaa.domain.MoocUsersRoles;
import org.apache.ibatis.annotations.Param;

public interface MoocUsersRolesMapper {
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int insert(MoocUsersRoles record);

    int insertSelective(MoocUsersRoles record);
}