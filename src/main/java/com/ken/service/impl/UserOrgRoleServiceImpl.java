package com.ken.service.impl;

import com.ken.entity.common.UserOrgRole;
import com.ken.mapper.UserOrgRoleMapper;
import com.ken.service.IUserOrgRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yhq
 * @date 2019/3/24
 */
@Service
public class UserOrgRoleServiceImpl implements IUserOrgRoleService {

    @Autowired
    private UserOrgRoleMapper userOrgRoleMapper;

    @Override
    public void saveUserOrgRole(UserOrgRole userOrgRole) {
        userOrgRoleMapper.saveUserOrgRole(userOrgRole);
    }
}
