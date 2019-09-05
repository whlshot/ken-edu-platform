package com.ken;

import org.junit.Test;

import java.util.Enumeration;

/**
 * @author yhq
 * @date 2019/8/29
 */
public class TestMain {

    @Test
    public void test(){
        System.out.println(System.getProperty("user.dir"));
        Enumeration enumeration=System.getProperties().propertyNames();
        while (enumeration.hasMoreElements()){
            String str=enumeration.nextElement().toString();
            System.out.println(str+"  === "+System.getProperty(str));
        }
    }
}
