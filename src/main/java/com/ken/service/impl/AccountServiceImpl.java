package com.ken.service.impl;

import com.ken.common.constant.Constant;
import com.ken.entity.User;
import com.ken.entity.common.Account;
import com.ken.entity.common.UserOrgRole;
import com.ken.mapper.AccountMapper;
import com.ken.service.IAccountService;
import com.ken.service.IUserOrgRoleService;
import com.ken.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yhq
 * @date 2019/3/21
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IUserService userService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IUserOrgRoleService userOrgRoleService;

    @Override
    public void saveAccount(Account account) {
        accountMapper.saveAccount(account);
    }

    @Transactional
    @Override
    public void saveAccount(Integer schoolId, Account account) {
        User user = new User();
        userService.saveUser(user);
        UserOrgRole userOrgRole = new UserOrgRole();
        userOrgRole.setUserId(user.getId());
        userOrgRole.setOrgId(schoolId);
        userOrgRole.setRoleId(1002);
        userOrgRoleService.saveUserOrgRole(userOrgRole);
        account.setUserId(user.getId());
        account.setPassword(Constant.DEFAULT_PWD);
        this.saveAccount(account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return null;
    }

    @Override
    public Account getAccountByPhone(String phone) {
        return null;
    }

    @Override
    public Account getAccountByLoginName(String loginName, String password) {
        return null;
    }
}
