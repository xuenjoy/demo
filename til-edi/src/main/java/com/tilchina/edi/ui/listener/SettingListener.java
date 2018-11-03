package com.tilchina.edi.ui.listener;

import com.tilchina.edi.ui.MainWindow;
import com.tilchina.edi.ui.UIInit;
import com.tilchina.edi.util.AccessDBUtil;
import com.tilchina.edi.util.LoggerUtil;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * 设置页面事件监听
 */
public class SettingListener {
    private static final Logger logger= LoggerUtil.getLogger(SettingListener.class);

    public static void addListener(){
        //测试数据连接
        MainWindow.mainWindow.getConnectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.mainWindow.getConnectButton().setEnabled(false);
                MainWindow.mainWindow.getProgressDb().setIndeterminate(true);
                MainWindow.mainWindow.getProgressDb().setString("正在连接");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String url=MainWindow.mainWindow.getDbTextField().getText();
                            String password=MainWindow.mainWindow.getPasswordTextField().getText();
                            Connection conn=AccessDBUtil.getConn(url,password);
                            if (conn == null) {
                                JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "连接失败", "失败",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "连接成功！", "成功",
                                        JOptionPane.INFORMATION_MESSAGE);
                                try {
                                    conn.close();
                                } catch (Exception el) {
                                    el.printStackTrace();
                                }
                            }
                        }catch (Exception el){
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "连接失败！\n\n" + el.getMessage(), "失败",
                                    JOptionPane.ERROR_MESSAGE);
                            logger.error(el.getMessage());
                        }finally {
                            MainWindow.mainWindow.getProgressDb().setIndeterminate(false);
                            MainWindow.mainWindow.getProgressDb().setString("连接完毕");
                            MainWindow.mainWindow.getConnectButton().setEnabled(true);
                        }

                    }
                }).start();
            }
        });

        //保存数据库连接
        MainWindow.mainWindow.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.mainWindow.getSaveButton().setEnabled(false);
                try{
                    UIInit.configer.setDBUrl(MainWindow.mainWindow.getDbTextField().getText());
                    UIInit.configer.setDBPassword(MainWindow.mainWindow.getPasswordTextField().getText());
                    UIInit.configer.save();

                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "保存成功！", "成功",
                            JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception el){
                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "保存失败！\n\n" + el.getMessage(), "失败",
                            JOptionPane.ERROR_MESSAGE);
                    logger.error(el);
                }finally {
                    MainWindow.mainWindow.getSaveButton().setEnabled(true);
                }
            }
        });
    }
}
