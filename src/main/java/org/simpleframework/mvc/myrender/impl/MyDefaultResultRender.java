package org.simpleframework.mvc.myrender.impl;

import org.simpleframework.mvc.MyRequestProcessorChain;
import org.simpleframework.mvc.myrender.MyResultRender;

/**
 * @program: simpleframework
 * @description: 默认渲染器
 * @author: 十字街头的守候
 * @create: 2021-01-26 10:40
 **/
public class MyDefaultResultRender implements MyResultRender {
    @Override
    public void render(MyRequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().write(requestProcessorChain.getResponseCode());
    }
}
