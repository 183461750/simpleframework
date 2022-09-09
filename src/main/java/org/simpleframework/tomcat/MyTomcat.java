package org.simpleframework.tomcat;

import org.simpleframework.mvc.MyDispatcherServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * @author fa
 */
public class MyTomcat {

    private int port = 8080;

    private Map<String, String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        // 初始化URL与对应处理的servlet的关系
        initServletMapping();

        Executors.newSingleThreadExecutor().execute(() -> {

            try (ServerSocket serverSocket = new ServerSocket(port);) {
                System.out.println("MyTomcat is start...");
                while (true) {
                    OutputStream outputStream = null;
                    try {
                        Socket socket = serverSocket.accept();

                        InputStream inputStream = socket.getInputStream();
                        outputStream = socket.getOutputStream();

                        MyRequest myRequest = new MyRequest(inputStream);
                        MyResponse myResponse = new MyResponse(outputStream);

                        System.out.println("accept: myRequest: " + myRequest + ", myResponse: " + myResponse);

                        // 请求分发
                        dispatch(myRequest, myResponse);

                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();

                        if (Objects.nonNull(outputStream)) {
                            String respStr = "HTTP/1.1 200 OK\n" +
                                    "Content-Type: text/html" +
                                    "\n\n" +
                                    "<html><body>" +
                                    "exception: " + e.getMessage() +
                                    "</body></html>";

                            outputStream.write(respStr.getBytes(StandardCharsets.UTF_8));
                            outputStream.close();

                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String url = myRequest.getUrl();
        String clazz = urlServletMap.get(url);

        if ("/favicon.ico".equalsIgnoreCase(url)) {
            return;
        }

        try {
            System.out.println("url: " + url + ", clazz: " + clazz);
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.getDeclaredConstructor().newInstance();

            myServlet.service(myRequest, myResponse);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8080;

        new MyTomcat(port).start();

        // 示例: localhost:8080/girl
        System.out.println("启动后，浏览器访问: localhost:" + port);

    }

}
