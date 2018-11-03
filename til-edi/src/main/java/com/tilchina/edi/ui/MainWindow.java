package com.tilchina.edi.ui;

import com.tilchina.edi.ui.listener.DataUpdateListener;
import com.tilchina.edi.ui.listener.ExcelListener;
import com.tilchina.edi.ui.listener.SettingListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * 主界面
 */
public class MainWindow {
    private JPanel mainPanel;
    private JTabbedPane tabbedPanel;
    //导入导出界面
    private JPanel excelPanel;
    //系统设置界面
    private JPanel settingPanel;
    private JScrollPane settingScrollPane;
    private JTextField passwordTextField;
    private JTextField dbTextField;
    private JProgressBar progressDb;
    //系统设计界面-数据库设置-测试连接
    private JButton connectButton;
    //系统设计界面-数据库设置-保存
    private JButton saveButton;

    //导入导出界面-文件路径
    private JTextField fileTextField;
    //导入导出界面-浏览文件
    private JButton fileButton;
    //导入导出界面-导入
    private JButton importButton;
    //导入导出页面-导入类型
    private JComboBox importComboBox;
    private JTextArea noTextArea;
    //导入导出页面-导导出类型
    private JComboBox exportComboBox;
    //导入导出界面-导出
    private JButton exportButton;
    //导入导出界面-进度条
    private JProgressBar totalProgressBar;
    private JLabel versionLabel;

    //数据更新页面
    private JPanel dataUpdatePanel;
    //票据类型-提单号
    private JTextArea textBlBill;
    //票据类型-保函号
    private JTextField textLetterNo;
    //票据类型-当前进度
    private JProgressBar progressBill;
    //票据类型-下拉框
    private JComboBox comboBillType;
    //票据类型-确认按钮
    private JButton buttonBill;
    //关联号-提单号
    private JTextArea textBlCorrelate;


    //关联号-确认按钮
    private JButton buttonCorrelate;
    //关联号-当前进度
    private JProgressBar progressCorrelate;


    public static JFrame frame;

    public static MainWindow mainWindow;

    private Logger logger=Logger.getLogger(MainWindow.class);
    public MainWindow(){

    }

    public static void main(String[] args){
        //初始化主题
        UIInit.initTheme();
        //设置字体
        UIInit.initFont();
        // Windows系统状态栏图标
        frame = new JFrame(UIConstants.APP_NAME);
        frame.setIconImage(UIConstants.IMAGE_ICON);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        frame.setBounds((int) (screenSize.width * 0.1), (int) (screenSize.height * 0.08), (int) (screenSize.width * 0.8),
                (int) (screenSize.height * 0.8));

        Dimension preferSize = new Dimension((int) (screenSize.width * 0.8),
                (int) (screenSize.height * 0.8));
        frame.setPreferredSize(preferSize);

        mainWindow = new MainWindow();

        frame.setContentPane(mainWindow.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        UIInit.initOthers();
        UIInit.initAllTab();

        //添加事件
        SettingListener.addListener();
        ExcelListener.addListener();
        DataUpdateListener.addListener();

    }

    public JScrollPane getSettingScrollPane() {
        return settingScrollPane;
    }
    public JPanel getExcelPanel() {
        return excelPanel;
    }
    public JLabel getVersionLabel() {
        return versionLabel;
    }
    public JTextField getPasswordTextField() {
        return passwordTextField;
    }
    public JTextField getDbTextField() {
        return dbTextField;
    }
    public JButton getConnectButton() {
        return connectButton;
    }
    public JButton getSaveButton() {
        return saveButton;
    }
    public JPanel getSettingPanel() {
        return settingPanel;
    }
    public JButton getFileButton() {
        return fileButton;
    }

    public JButton getImportButton() {
        return importButton;
    }

    public JComboBox getImportComboBox() {
        return importComboBox;
    }

    public JTextArea getNoTextArea() {
        return noTextArea;
    }

    public JComboBox getExportComboBox() {
        return exportComboBox;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public JTextField getFileTextField() {
        return fileTextField;
    }
    public JProgressBar getTotalProgressBar() {
        return totalProgressBar;
    }
    public JPanel getDataUpdatePanel() {
        return dataUpdatePanel;
    }

    public void setDataUpdatePanel(JPanel dataUpdatePanel) {
        this.dataUpdatePanel = dataUpdatePanel;
    }

    public JTextArea getTextBlBill() {
        return textBlBill;
    }

    public void setTextBlBill(JTextArea textBlBill) {
        this.textBlBill = textBlBill;
    }

    public JTextField getTextLetterNo() {
        return textLetterNo;
    }

    public void setTextLetterNo(JTextField textLetterNo) {
        this.textLetterNo = textLetterNo;
    }

    public JProgressBar getProgressBill() {
        return progressBill;
    }

    public void setProgressBill(JProgressBar progressBill) {
        this.progressBill = progressBill;
    }

    public JComboBox getComboBillType() {
        return comboBillType;
    }

    public void setComboBillType(JComboBox comboBillType) {
        this.comboBillType = comboBillType;
    }

    public JButton getButtonBill() {
        return buttonBill;
    }

    public void setButtonBill(JButton buttonBill) {
        this.buttonBill = buttonBill;
    }

    public JTextArea getTextBlCorrelate() {
        return textBlCorrelate;
    }

    public void setTextBlCorrelate(JTextArea textBlCorrelate) {
        this.textBlCorrelate = textBlCorrelate;
    }

    public JButton getButtonCorrelate() {
        return buttonCorrelate;
    }

    public void setButtonCorrelate(JButton buttonCorrelate) {
        this.buttonCorrelate = buttonCorrelate;
    }

    public JProgressBar getProgressCorrelate() {
        return progressCorrelate;
    }

    public void setProgressCorrelate(JProgressBar progressCorrelate) {
        this.progressCorrelate = progressCorrelate;
    }
    public JProgressBar getProgressDb() {
        return progressDb;
    }

    public void setProgressDb(JProgressBar progressDb) {
        this.progressDb = progressDb;
    }
}
