package org.simpleframework.test;

import org.simpleframework.core.BeanContainer;

class BeanContainerTest {
    private static BeanContainer beanContainer;

    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    public static void loadBeansTest() {
        //在没有加载bean时查看容器是否被加载
        System.out.println("isLoaded: " + beanContainer.isLoaded());
        //加载自己指定包下的bean,我这里是“tjoker”包
        //所在的包下的类应该被定义的注解标记
        beanContainer.loadBeans("org.simpleframework.test");
        //测试当前bean容量是否也我们预期的6是否一样
        System.out.println("当前容器数量: " + beanContainer.size());
        //测试当前bean容器是否被加载
        System.out.println("isLoaded: " + beanContainer.isLoaded());
    }

    public static void main(String[] args) {
        init();
        loadBeansTest();
    }

}