package com.ken.service.impl;

import com.ken.entity.School;
import com.ken.entity.common.Account;
import com.ken.mapper.SchoolMapper;
import com.ken.service.IAccountService;
import com.ken.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yhq
 * @date 2019/1/21
 */
@Service
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private IAccountService accountService;

    @Override
    @Transactional
    public void saveSchool(School school) {
        schoolMapper.saveSchool(school);
        Account account = new Account();
        account.setUserName(school.getContactName());
        account.setEmail(school.getContactEmail());
        account.setPhone(school.getContactPhone());
        accountService.saveAccount(school.getId(), account);
    }

    @Override
    public boolean delSchool(Integer id) {
        return schoolMapper.delSchool(id);
    }

    @Override
    public boolean updateSchool(School school) {
        return schoolMapper.updateSchool(school);
    }

    @Override
    public School getSchool(Integer id) {
        return schoolMapper.getSchoolById(id);
    }
}
