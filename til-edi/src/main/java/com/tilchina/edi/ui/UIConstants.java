package com.tilchina.edi.ui;

import java.awt.*;

/**
 * UI常量类
 */
public class UIConstants {
    /**
     * 软件名称,版本
     */
    public final static String APP_NAME = "亿通小助手";
    public final static String APP_VERSION = "v_0.1";

    /**
     * 主窗口图标
     */
    public final static Image IMAGE_ICON = Toolkit.getDefaultToolkit()
            .getImage(MainWindow.class.getResource("/icon/logo.png"));

    /**
     * 软件版本检查url
     */
    public final static String CHECK_VERSION_URL = "https://raw.githubusercontent.com/rememberber/WePush/master/src/main/resources/version_summary.json";

}
