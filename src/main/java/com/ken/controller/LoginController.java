package com.ken.controller;

import com.ken.common.model.ResultInfo;
import com.ken.entity.common.Account;
import com.ken.service.IAccountService;
import com.ken.utils.EmptyUtils;
import com.ken.utils.RegexValidatorUtil;
import com.ken.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhq
 * @date 2019/3/24
 */
@RestController
public class LoginController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public ResultInfo login(String loginName, String password) {
        if (EmptyUtils.isEmpty(loginName) || EmptyUtils.isEmpty(password)) {
            return ResultUtils.error("登录名或者密码为空");
        }
        Account account = null;
        if (RegexValidatorUtil.isEmail(loginName)) {
            account = accountService.getAccountByEmail(loginName);
        } else if (RegexValidatorUtil.isMobile(loginName)) {
            account = accountService.getAccountByPhone(loginName);
        } else {
            account = accountService.getAccountByLoginName(loginName, password);
        }
        if (account == null) {
            return ResultUtils.error("登录名或者密码错误");
        }
        return ResultUtils.success("恭喜你，登录成功");
    }

    @GetMapping("logout")
    public ResultInfo logout() {
        return null;
    }
}
