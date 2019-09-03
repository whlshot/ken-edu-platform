package com.ken.mapper;

import com.ken.entity.common.UserOrgRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrgRoleMapper {

    boolean saveUserOrgRole(@Param("userOrgRole") UserOrgRole userOrgRole);
}
