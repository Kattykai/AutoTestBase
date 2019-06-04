package com.testcase.caseall;

import org.testng.annotations.*;

public class SuitConfig {

    @BeforeSuite
    public void beforeSuit(){
        System.out.println("被@BeforeSuite注解的方法，将会在testng定义的xml根元素里面的所有执行之前运行。");
    }


    @BeforeClass
    public void beforeClass(){
        System.out.println("被@BeforeClass注解的方法，将会在当前测试类的第一个测试方法执行之前运行。");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("被@AfterClass注解的方法，将会在当前测试类的最后一个测试方法执行之后运行。");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("被@BeforeMethod注解的方法，将会在当前测试类的每一个测试方法执行之前运行。");
    }


    @AfterMethod
    public void afterMethod(){
        System.out.println("被@AfterMethod注解的方法，将会在当前测试类的每一个测试方法执行之后运行。");
    }

    @AfterSuite
    public void afterSuit(){
        System.out.println("被@AfterSuite注解的方法，将会在testng定义的xml根元素里面的所有执行之后运行。");
    }
}
