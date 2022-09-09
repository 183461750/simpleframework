package org.simpleframework.mvc.render;

import org.simpleframework.mvc.MyRequestProcessorChain;
import org.simpleframework.mvc.RequestProcessorChain;

/**
 * 渲染请求结果
 */
public interface MyResultRender {
    //执行渲染
    void render(MyRequestProcessorChain requestProcessorChain)throws Exception;
}
