package org.simpleframework.mvc.myrender.impl;

import com.google.gson.Gson;
import org.simpleframework.mvc.MyRequestProcessorChain;
import org.simpleframework.mvc.myrender.MyResultRender;

import java.io.PrintWriter;

/**
 * @program: simpleframework
 * @description: Json渲染器
 * @author: 十字街头的守候
 * @create: 2021-01-26 10:40
 **/
public class MyJsonResultRender implements MyResultRender {
    private Object jsonData;
    private Gson gson=new Gson();

    public MyJsonResultRender(Object result) {
        this.jsonData=result;
    }

    @Override
    public void render(MyRequestProcessorChain requestProcessorChain) throws Exception {
        //设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        //设置响应编码
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");

        requestProcessorChain.getResponse().writeJson(gson.toJson(jsonData));

    }
}
