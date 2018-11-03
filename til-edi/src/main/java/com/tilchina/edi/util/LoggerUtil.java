package com.tilchina.edi.util;


import com.tilchina.edi.ui.MainWindow;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil  {
    public static Logger getLogger(Class clazz) {
        String classPath= MainWindow.class.getResource("/log4j.properties").getPath();
        PropertyConfigurator.configure(classPath);
        return LogManager.getLogger(clazz.getName());
    }
}
