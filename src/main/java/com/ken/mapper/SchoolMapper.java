package com.ken.mapper;

import com.ken.entity.School;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolMapper {

    boolean saveSchool(School school);

    boolean delSchool(@Param("id") Integer id);

    boolean updateSchool(@Param("school") School school);

    School getSchoolById(@Param("id") Integer id);
}
