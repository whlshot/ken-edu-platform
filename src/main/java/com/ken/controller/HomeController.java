package com.ken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhq
 * @date 2018/12/24
 */
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/index")
    public String home() {
        return "home";
    }

    @GetMapping("/testAsync")
    @ResponseBody
    public String testAsync() {
        System.out.println("main " + Thread.currentThread().getName());
        homeService.async();
        return "success";
    }


}
