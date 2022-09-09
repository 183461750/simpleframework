package org.simpleframework.mvc;

import org.simpleframework.aop.AspectWeaver;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;
import org.simpleframework.mvc.processor.MyRequestProcessor;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.processor.impl.ControllerRequestProcessor;
import org.simpleframework.mvc.processor.impl.MyControllerRequestProcessor;
import org.simpleframework.mvc.processor.impl.MyPreRequestProcessor;
import org.simpleframework.mvc.processor.impl.PreRequestProcessor;
import org.simpleframework.tomcat.MyRequest;
import org.simpleframework.tomcat.MyResponse;
import org.simpleframework.tomcat.MyServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: simpleframework
 * @description: dispatcherServlet分发器
 * @author: 十字街头的守候
 * @create: 2021-01-16 20:44
 **/
@WebServlet("/*")
public class MyDispatcherServlet extends MyServlet {

    List<MyRequestProcessor> PROCESSOR = new ArrayList<>();

    public void init() {
        //1、初始化容器
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("tjoker");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        //2、初始化请求处理器责任链
        PROCESSOR.add(new MyPreRequestProcessor());
//        PROCESSOR.add(new StaticResourceRequestProcessor(getServletContext()));
//        PROCESSOR.aMy(new JspRequestProcessor(getServletContext()));
        PROCESSOR.add(new MyControllerRequestProcessor());
    }

    @Override
    public void service(MyRequest req, MyResponse resp) {
        //1、创建责任链对象实例
        MyRequestProcessorChain requestProcessorChain = new MyRequestProcessorChain(PROCESSOR.iterator(), req, resp);
        //2、通过责任链模式来依次调用请求处理对请求进行处理
        requestProcessorChain.doRequestProcessorChain();
        //3、对处理结果进行渲染
        requestProcessorChain.doRender();
    }


    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {

    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {

    }

}
