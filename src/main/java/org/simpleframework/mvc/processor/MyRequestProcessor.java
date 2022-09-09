package org.simpleframework.mvc.processor;

import org.simpleframework.mvc.MyRequestProcessorChain;

public interface MyRequestProcessor {
    boolean process(MyRequestProcessorChain requestProcessorChain)throws Exception;
}
