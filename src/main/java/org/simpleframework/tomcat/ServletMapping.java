package org.simpleframework.tomcat;

import lombok.Data;

/**
 * @author fa
 */
@Data
public class ServletMapping {

    private String servletName;
    private String url;
    private String clazz;

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }

}
