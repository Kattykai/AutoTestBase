package com.testcase.caseall;

import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;


public class TestCaseDemo {



    @Test
    public void testcase01() {
        System.out.println("被@Test注解的方法");
    }

    /**
     * 忽略测试@Test（enable = true）
     */
    @Test(enabled = true, groups = "client")
    public void testcase02() {
        System.out.println("被@Test（enable = true）注解的方法");
    }

    @Test(enabled = false)
    public void testcase03() {
        System.out.println("被@Test（enable = true）注解的方法");
    }

    /**
     * 组测试@Test(groups = "client")
     */
    @Test(groups = "client")
    public void client() {
        System.out.println("This is client method");
    }

    @Test(groups = "server")
    public void server() {
        System.out.println("This is server method");
    }

    @BeforeGroups("client")
    public void beforeGroups() {
        System.out.println("This is before client beforeGroups");
    }

    @AfterGroups("client")
    public void afterGroups() {
        System.out.println("This is before client afterGroups");
    }

    /**
     * 异常测试
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFaild() {
        System.out.println("This is RumTimeException:Faild");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSucess() {
        System.out.println("This is RumTimeException:Success");
        throw new RuntimeException();
    }

    /**
     * 依赖测试
     */
    @Test
    public void dependParent() {
        System.out.println("This is dependParent");
//        throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"dependParent"})
    public void dependChild() {
        System.out.println("This is dependChild");
    }

    /**
     * 参数测试:通过外部传数据
     */

    @Test
    @Parameters({"name", "age"})
    public void Parameters(String name, int age) {
        System.out.println("MyName is :" + name);
        System.out.println("MyAge is :" + age);
    }

    /**
     * 参数测试:通过内部传数据
     */
    @Test(dataProvider = "data")
    public void ParametersFromMethod(String name, int age) {
        System.out.println("MyName is :" + name);
        System.out.println("MyAge is :" + age);
    }

    @DataProvider(name = "data")
    public Object[][] provideData() {
        Object[][] o = new Object[][]{
                {"Auto", 10},
                {"Auto1", 20},
                {"Auto2", 30},
        };
        return o;
    }

    /**
     * 根据不同方法传递不同参数
     *
     * @param name
     * @param age
     */
    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test111方法 name=" + name + ";age=" + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test222方法 name=" + name + ";age=" + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method) {
        Object[][] result = null;

        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"zhangsan", 20},
                    {"lisi", 25}
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"wangwu", 50},
                    {"zhaoliu", 60}
            };
        }

        return result;
    }

    /**
     * 多线程运行
     */
    @Test(invocationCount = 10, threadPoolSize = 3)
    public void multiThread() {
        System.out.println("This is multiThread Test");
        System.out.printf("Thread ID：%s%n", Thread.currentThread().getId());
    }
}
