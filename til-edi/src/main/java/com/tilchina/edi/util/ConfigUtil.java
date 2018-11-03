package com.tilchina.edi.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigUtil {
    private static final Logger logger=Logger.getLogger(ConfigUtil.class);
    private File file;

    private PropertyUtil props;

    private static ConfigUtil instance=new ConfigUtil();


    public static ConfigUtil getInstance(){
        return instance;
    }

    private ConfigUtil(){
        file = new File(SystemUtil.configHome + "config" + File.separator + "config.properties");
        File configDir = new File(SystemUtil.configHome + "config" + File.separator);
        if (!file.exists()) {
            try {
                configDir.mkdirs();
                file.createNewFile();
                initProperties();
            } catch (IOException e) {
                logger.error(e);
            }
        }
        props = new PropertyUtil(file);
    }

    /**
     * 初始化数据
     */
    private void initProperties(){
        props = new PropertyUtil(file);

        this.setTheme("Darcula");
        this.setFont( "Microsoft YaHei UI");
        this.setFontSize( 18);

        this.save();
    }


    /**
     * 存盘
     */
    public void save() {
        try {
            props.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void setProps(String key, String value) {
        props.setProperty(key, value);
    }

    public String getProps(String key) {
        return props.getProperty(key);
    }

    public String getTheme() {
        return props.getProperty("setting.appearance.theme");
    }

    public void setTheme(String theme) {
        props.setProperty("setting.appearance.theme", theme);
    }


    public String getFont() {
        return props.getProperty("setting.appearance.font");
    }

    public void setFont(String font) {
        props.setProperty("setting.appearance.font", font);
    }

    public int getFontSize() {
        return props.getInt("setting.appearance.fontSize");
    }

    public void setFontSize(int fontSize) {
        props.setProperty("setting.appearance.fontSize", fontSize);
    }

    public void setDBUrl(String url){
            props.setProperty("setting.access.url", url);
    }

    public String getDBUrl(){
        return props.getProperty("setting.access.url");
    }
    public void setDBPassword(String password){
        props.setProperty("setting.access.password", password);
    }
    public String getDBPassword(){
        return props.getProperty("setting.access.password");
    }
}
