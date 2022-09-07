package org.simpleframework.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fa
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("findGirl", "/girl", "org.simpleframework.tomcat.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("helloWorld", "/world", "org.simpleframework.tomcat.HelloWorldServlet"));
    }

}
