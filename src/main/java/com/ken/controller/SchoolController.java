package com.ken.controller;

import com.ken.common.model.ResultInfo;
import com.ken.entity.School;
import com.ken.service.ISchoolService;
import com.ken.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yhq
 * @date 2019/1/17
 */
@RestController
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    private ISchoolService schoolService;

    /**
     * 注册学校
     * 1.保存学校信息
     * 2.保存用户信息
     * 3.保存用户组织角色关系
     * 4.保存登录账号
     * 5.发送短信 TODO
     *
     * @param school
     * @author yhq
     * @date 2019/3/21
     */
    @PostMapping("/registerSchool")
    public ResultInfo createSchool(@RequestBody School school) {
        schoolService.saveSchool(school);
        return ResultUtils.success();
    }

    @DeleteMapping("/delSchool/{id}")
    public ResultInfo delSchool(@PathVariable Integer id) {
        schoolService.delSchool(id);
        return ResultUtils.success();
    }

    @PutMapping("/updateSchool")
    public ResultInfo updateSchool(@RequestBody School school) {
        schoolService.updateSchool(school);
        return ResultUtils.success();
    }

    @GetMapping("/getSchool/{id}")
    public ResultInfo getSchool(@PathVariable Integer id) {
        School school = schoolService.getSchool(id);
        return ResultUtils.success(school);
    }

}
