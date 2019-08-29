package com.ken.service;

import com.ken.entity.School;

public interface ISchoolService {

    void saveSchool(School school);

    boolean delSchool(Integer id);

    boolean updateSchool(School school);

    School getSchool(Integer id);
}
