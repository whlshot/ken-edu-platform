package com.ken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhq
 * @date 2019/8/29
 */
public class TestMain {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
