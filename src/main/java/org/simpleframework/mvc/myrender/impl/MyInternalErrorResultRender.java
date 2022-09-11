package org.simpleframework.mvc.myrender.impl;

import org.simpleframework.mvc.MyRequestProcessorChain;
import org.simpleframework.mvc.myrender.MyResultRender;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: simpleframework
 * @description: 异常渲染器
 * @author: 十字街头的守候
 * @create: 2021-01-26 10:40
 **/
public class MyInternalErrorResultRender implements MyResultRender {
    private String erroMsg;

    public MyInternalErrorResultRender(String erroMsg) {
        this.erroMsg = erroMsg;
    }
    @Override
    public void render(MyRequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().write(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,erroMsg);
    }
}
