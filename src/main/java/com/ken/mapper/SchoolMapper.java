package com.ken.mapper;

import com.ken.entity.School;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolMapper {

    boolean insertSchool(@Param("school") School school);

    boolean delSchool(@Param("id") Integer id);

    boolean updateSchool(@Param("school") School school);

    School selectSchoolById(@Param("id") Integer id);
}
