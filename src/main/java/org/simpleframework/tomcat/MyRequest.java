package org.simpleframework.tomcat;

import lombok.Data;
import org.simpleframework.mvc.myrender.MyRequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fa
 */
@Data
public class MyRequest {

    private String url;
    private String method;
    private String characterEncoding;
    private Map<String, String[]> parameterMap;
    private Map<String, Object> attributeMap;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }

        if (httpRequest.length() < 1) {
            return;
        }

        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1].split("\\?")[0];
        method = httpHead.split("\\s")[0];

        // 处理请求参数
        handleParam(httpRequest);

        System.out.println(this);
    }

    /**
     * 处理请求参数
     *
     * @param httpRequest
     */
    private void handleParam(String httpRequest) {
        parameterMap = new HashMap<>();

        String httpHead = httpRequest.split("\n")[0];
        String[] strs = httpHead.split("\\s")[1].split("\\?");

        String allQueryStr = "";
        // NOTE：多个问号的情况，考虑用url encode处理
        if (strs.length > 1) {
            allQueryStr = strs[1];
        }
        if (allQueryStr.length() < 1) {
            return;
        }
        String[] queryStrs = allQueryStr.split("&");
        for (int i = 0; i < queryStrs.length; i++) {
            String queryStr = queryStrs[0];
            String[] queryStrItems = queryStr.split("=");
            parameterMap.put(queryStrItems[0], new String[]{queryStrItems[1]});
        }

    }

    public void setAttribute(String name, Object o) {
        attributeMap.put(name, o);
    }

    public MyRequestDispatcher getRequestDispatcher(String path) {
        return new MyRequestDispatcher() {
            @Override
            public void forward(MyRequest request, MyResponse response) throws ServletException, IOException {
                request.setUrl(path);
            }

            @Override
            public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {

            }
        };
    }

}
