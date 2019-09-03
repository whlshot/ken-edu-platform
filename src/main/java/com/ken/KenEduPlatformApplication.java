package com.ken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.ken.mapper")
public class KenEduPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(KenEduPlatformApplication.class, args);
    }

}
