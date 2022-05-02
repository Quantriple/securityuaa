package com.uaa.repository;

import com.uaa.domain.MoocRolesPermissions;
import org.apache.ibatis.annotations.Param;

public interface MoocRolesPermissionsMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    int insert(MoocRolesPermissions record);

    int insertSelective(MoocRolesPermissions record);
}