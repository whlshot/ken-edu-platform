package com.ken.service;

import com.ken.entity.common.Account;

public interface IAccountService {

    void saveAccount(Account account);

    void saveAccount(Integer schoolId, Account account);

    void updateAccount(Account account);

    Account getAccountByEmail(String email);

    Account getAccountByPhone(String phone);

    Account getAccountByLoginName(String loginName, String password);
}
