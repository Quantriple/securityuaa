package com.uaa.repository;

import com.uaa.domain.MoocRoles;

public interface MoocRolesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoocRoles record);

    int insertSelective(MoocRoles record);

    MoocRoles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoocRoles record);

    int updateByPrimaryKey(MoocRoles record);
}