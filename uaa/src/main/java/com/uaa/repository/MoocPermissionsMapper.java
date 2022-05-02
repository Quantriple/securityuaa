package com.uaa.repository;

import com.uaa.domain.MoocPermissions;

public interface MoocPermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoocPermissions record);

    int insertSelective(MoocPermissions record);

    MoocPermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoocPermissions record);

    int updateByPrimaryKey(MoocPermissions record);
}