package org.simpleframework.test;

import org.simpleframework.core.BeanContainer;
import org.simpleframework.mvc.DispatcherServlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author fa
 */
public class DispatcherServletTest {

    private static final DispatcherServlet dispatcherServlet;
    private static final BeanContainer beanContainer;

    static {
        beanContainer = BeanContainer.getInstance();


        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();

        loadBeansTest();
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
//        ServletRequest req = new HttpServletRequestWrapper();
//        ServletResponse res;
//        dispatcherServlet.service();
    }

}
