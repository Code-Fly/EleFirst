/**
 *
 */
package com.elefirst.base.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author Barrie
 */
public class Const {
    public static final String SUPER_USER_NAME = "admin";

    public static final String CONFIG_PATH_SETTING = "settings.properties";

    public static final String CONFIG_KEY_SUPER_USER_PASSWORD = "system.password";

    public static final String CONFIG_KEY_AREA_ID = "system.areaId";

    public static final String CONFIG_KEY_AREA_NAME = "system.areaName";

    public static final String CONFIG_KEY_ICP = "system.icp";

    public static final String CONFIG_KEY_INDEX_LOGO_PATH = "system.indexLogoPath";

    public static final String CONFIG_KEY_LOGIN_LOGO_PATH = "system.loginLogoPath";

    public static final String CONFIG_KEY_REPORT_MAX_DAY_INTERVAL = "report.maxDayInterval";

    public static final String CONFIG_KEY_REPORT_MAX_MONTH_INTERVAL = "report.maxMonthInterval";

    public static final String CONFIG_KEY_REPORT_MAX_YEAR_INTERVAL = "report.maxYearInterval";

    public static final String CONFIG_KEY_REPORT_ENERGY_BY_HOUR_ENABLE = "report.EnergyByHour.enable";
    
    public static final String CONFIG_KEY_REPORT_DISPLAY_BY_DAILY_ENABLE = "report.DisplayByDaily.enable";
    
    public static final String CONFIG_KEY_REPORT_ELEC_BY_DAILY_ENABLE = "report.ElecByDaily.enable";
    
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
