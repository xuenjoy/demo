package com.tilchina.edi.util;

import java.io.File;

/**
 * 系统工具
 */
public class SystemUtil {
    public static String osName = System.getProperty("os.name");
    public static String configHome = System.getProperty("user.home") + File.separator + ".tilEdi"
            + File.separator;

    public static boolean isMacOs() {
        if (osName.contains("Mac")) {
            return true;
        } else {
            return false;
        }
    }
}
