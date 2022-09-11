package org.simpleframework.tomcat;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author fa
 */
@Data
public class MyResponse {

    private OutputStream outputStream;
    private String contentType;
    private String characterEncoding;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public PrintWriter getWriter() {
        return new PrintWriter(outputStream);
    }

    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;charset=").append(characterEncoding)
                .append("\r\n\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");

        String respStr = httpResponse.toString();

//        respStr = "HTTP/1.1 200 OK\napplication/json;charset=UTF-8\n\n{}";

        System.out.println("====================respStr====================");
        System.out.println(respStr);
        System.out.println("====================respStr====================");

        outputStream.write(respStr.getBytes(characterEncoding));
        outputStream.close();

    }

    public void write(int code) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 ").append(code).append(" OK\n")
                .append("Content-Type: text/html;charset=").append(characterEncoding)
                .append("\r\n\n")
                .append("<html><body>")
                .append("nobody")
                .append("</body></html>");

        String respStr = httpResponse.toString();

//        respStr = "HTTP/1.1 200 OK\napplication/json;charset=UTF-8\n\n{}";

        System.out.println("====================respStr====================");
        System.out.println(respStr);
        System.out.println("====================respStr====================");

        outputStream.write(respStr.getBytes(characterEncoding));
        outputStream.close();

    }

    public void write(int code, String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 ").append(code).append(" OK\n")
                .append("Content-Type: text/html;charset=").append(characterEncoding)
                .append("\r\n\n")
                .append("<html><body>").append(content).append("</body></html>");

        String respStr = httpResponse.toString();

//        respStr = "HTTP/1.1 200 OK\napplication/json;charset=UTF-8\n\n{}";

        System.out.println("====================respStr====================");
        System.out.println(respStr);
        System.out.println("====================respStr====================");

        outputStream.write(respStr.getBytes(characterEncoding));
        outputStream.close();

    }

}
