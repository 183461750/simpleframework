package org.simpleframework.tomcat;

import lombok.Data;
import org.simpleframework.mvc.render.MyRequestDispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.InputStream;
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

        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println(this);
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
