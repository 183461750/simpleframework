package org.simpleframework.mvc.myrender;

import org.simpleframework.mvc.MyRequestProcessorChain;

/**
 * 渲染请求结果
 */
public interface MyResultRender {
    //执行渲染
    void render(MyRequestProcessorChain requestProcessorChain)throws Exception;
}
