package com.tilchina.edi.ui;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.tilchina.edi.util.ConfigUtil;
import com.tilchina.edi.util.LoggerUtil;
import com.tilchina.edi.util.PropertyUtil;
import com.tilchina.edi.util.SystemUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.util.Enumeration;

/**
 * 界面初始化
 */
public class UIInit {
    private static final Logger logger= LoggerUtil.getLogger(UIInit.class);
    private PropertyUtil props;

    //配置文件管理器对象
    public static ConfigUtil configer=ConfigUtil.getInstance();


    /**
     * 初始化主题
     */
    public static void initTheme(){
        try {
            switch (configer.getTheme()) {
                case "系统默认":
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                case "Windows":
                    UIManager.setLookAndFeel(WindowsLookAndFeel.class.getName());
                    break;
                case "Nimbus":
                    UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
                    break;
                case "Metal":
                    UIManager.setLookAndFeel(MetalLookAndFeel.class.getName());
                    break;
                case "Motif":
                    UIManager.setLookAndFeel(MotifLookAndFeel.class.getName());
                    break;
                case "Darcula":
                default:
                    UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * 初始化字体
     */
    public static void initFont(){
        //低分辨率屏幕字号初始化
        String lowDpiKey="lowDpiInit";
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();//得到屏幕的尺寸
        if(screenSize.width<-1366&& StringUtils.isEmpty(configer.getProps(lowDpiKey))){
            configer.setFontSize(15);
            configer.setProps(lowDpiKey, "true");
            configer.save();
        }

        // Mac高分辨率屏幕字号初始化
        String highDpiKey = "highDpiInit";
        if (SystemUtil.isMacOs() && StringUtils.isEmpty(configer.getProps(highDpiKey))) {
            configer.setFontSize(15);
            configer.setProps(highDpiKey, "true");
            configer.save();
        }

        Font fnt = new Font(configer.getFont(), Font.PLAIN, configer.getFontSize());
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

    /**
     * 其他初始化
     */
    public static void initOthers() {
        // 设置滚动条速度
        MainWindow.mainWindow.getSettingScrollPane().getVerticalScrollBar().setUnitIncrement(15);
        MainWindow.mainWindow.getSettingScrollPane().getVerticalScrollBar().setDoubleBuffered(true);

        // 设置版本
        MainWindow.mainWindow.getVersionLabel().setText("版本："+UIConstants.APP_VERSION);
    }

    public static void initAllTab(){
        initExcelTab();
        initSettingTab();
        initDataUpdateTab();
    }

    /**
     * 初始化导入导出界面
     */
    public static void initExcelTab(){
        MainWindow.mainWindow.getTextLetterNo().setText("HH201622000005");
        MainWindow.mainWindow.getComboBillType().setSelectedIndex(1);
    }

    /**
     * 初始化数据更新页面
     */
    public static void initDataUpdateTab(){

    }
    /**
     * 初始化系统设置界面
     */
    public static void initSettingTab(){
        MainWindow.mainWindow.getDbTextField().setText(configer.getDBUrl());
        MainWindow.mainWindow.getPasswordTextField().setText(configer.getDBPassword());
    }


}
