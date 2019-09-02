package com.ken.mapper;

import com.ken.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yhq
 * @date 2019/1/11
 */
@Repository
public interface UserMapper {

    void saveUser(User user);

    User getById(@Param("userId") Integer userId);
}
