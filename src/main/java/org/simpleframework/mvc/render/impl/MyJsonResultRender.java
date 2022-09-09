package org.simpleframework.mvc.render.impl;

import com.google.gson.Gson;
import org.simpleframework.mvc.MyRequestProcessorChain;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.render.MyResultRender;
import org.simpleframework.mvc.render.ResultRender;

import java.io.PrintWriter;

/**
 * @program: simpleframework
 * @description: Json渲染器
 * @author: 十字街头的守候
 * @create: 2021-01-26 10:40
 **/
public class MyJsonResultRender implements MyResultRender {
    private Object jsonData;
    public MyJsonResultRender(Object result) {
        this.jsonData=result;
    }

    @Override
    public void render(MyRequestProcessorChain requestProcessorChain) throws Exception {
        //设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        //设置响应编码
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        //响应流写入经过gson格式化之后的处理结果
        try(PrintWriter writer=requestProcessorChain.getResponse().getWriter()){
            Gson gson=new Gson();
            writer.write(gson.toJson(jsonData));
            writer.flush();
        }
    }
}
