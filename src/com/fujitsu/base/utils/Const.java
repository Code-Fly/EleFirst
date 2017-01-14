/**
 *
 */
package com.fujitsu.base.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author Barrie
 */
public class Const {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    public static final String PATH_SEPARATOR = File.separator;


    public static String getServerPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource(Const.PATH_SEPARATOR).getPath();
        path = "/" + path.substring(1, path.indexOf("/" + "classes")) + "/";
        return path;
    }

    public static String getServerUrl(HttpServletRequest request) {
        String path = request.getContextPath() + "/";
        int port = request.getServerPort();
        String basePath = null;
        if (80 == port) {
            basePath = request.getScheme() + "://" + request.getServerName() + path;
        } else {
            basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        }

        return basePath;
    }

}
