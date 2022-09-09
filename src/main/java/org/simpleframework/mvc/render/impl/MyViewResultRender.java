package org.simpleframework.mvc.render.impl;

import org.simpleframework.mvc.MyRequestProcessorChain;
import org.simpleframework.mvc.render.MyResultRender;
import org.simpleframework.mvc.type.ModelAndView;
import org.simpleframework.tomcat.MyRequest;
import org.simpleframework.tomcat.MyResponse;

import java.util.Map;

/**
 * @program: simpleframework
 * @description: 页面渲染器
 * @author: 十字街头的守候
 * @create: 2021-01-26 10:40
 **/
public class MyViewResultRender implements MyResultRender {

    public static final String VIEW_PATH = "/templates/";
    private ModelAndView modelAndView;

    /**
     * @param mv:
     * @Description:对传入的参数进行处理，并赋值给ModelAndView成员变量
     * @return: null
     */
    public MyViewResultRender(Object mv) {
        if (mv instanceof ModelAndView) {
            //1、如果传入的参数类型是ModelAndView ，则直接赋值给成员变量
            this.modelAndView = (ModelAndView) mv;
        } else if (mv instanceof String) {
            //2、如果传入的参数类型是String，则为视图，需要包装后才能赋值给成员变量
            this.modelAndView = new ModelAndView().setView((String) mv);
        } else {
            //针对其他情况，则直接抛出异常
            throw new RuntimeException("illegal request result type");
        }
    }

    /**
     * @param requestProcessorChain:
     * @Description:将请求处理结果按照视图路径转发至对应视图进行展示
     * @return: void
     */
    @Override
    public void render(MyRequestProcessorChain requestProcessorChain) throws Exception {
        MyResponse response = requestProcessorChain.getResponse();
        MyRequest request = requestProcessorChain.getRequest();
        String path = modelAndView.getView();
        Map<String, Object> model = modelAndView.getModel();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.getRequestDispatcher(VIEW_PATH + path).forward(request, response);

    }
}
