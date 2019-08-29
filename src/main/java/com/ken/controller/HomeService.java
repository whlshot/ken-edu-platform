package com.ken.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author yhq
 * @date 2019/5/8
 */
@Service
public class HomeService {

    @Async
    public void async() {
        System.out.println("Thread Name " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            System.out.println("执行完毕 " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
