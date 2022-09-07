package org.simpleframework.tomcat;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author fa
 */
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html")
                .append("\r\n\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");

        String respStr = httpResponse.toString();

//        respStr = "HTTP/1.1 200 OK\napplication/json;charset=UTF-8\n\n{}";

        System.out.println("====================respStr====================");
        System.out.println(respStr);
        System.out.println("====================respStr====================");

        outputStream.write(respStr.getBytes());
        outputStream.close();

    }

}
