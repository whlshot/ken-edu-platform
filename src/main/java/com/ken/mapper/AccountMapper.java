package com.ken.mapper;

import com.ken.entity.common.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    boolean saveAccount(Account account);
}
