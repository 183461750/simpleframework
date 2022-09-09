package org.simpleframework.test;

import org.simpleframework.core.BeanContainer;
import org.simpleframework.mvc.DispatcherServlet;
import org.simpleframework.mvc.MyDispatcherServlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author fa
 */
public class DispatcherServletTest {

    private static final MyDispatcherServlet dispatcherServlet;
    private static final BeanContainer beanContainer;

    static {
        beanContainer = BeanContainer.getInstance();


        dispatcherServlet = new MyDispatcherServlet();
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
        // TODO: 请求分配
//        dispatcherServlet.service();
    }

}
