package com.tilchina.edi.ui.listener;

import com.tilchina.edi.ui.MainWindow;
import com.tilchina.edi.util.AccessDBUtil;
import com.tilchina.edi.util.LoggerUtil;
import com.tilchina.edi.util.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设置页面事件监听
 */
public class DataUpdateListener {
    private static final Logger logger= LoggerUtil.getLogger(DataUpdateListener.class);
    public static void addListener(){
        //更新票据类型事件
        MainWindow.mainWindow.getButtonBill().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //锁定按钮
                MainWindow.mainWindow.getButtonBill().setEnabled(false);
                //提单号
                String blNumber= StringUtils.trim(MainWindow.mainWindow.getTextBlBill().getText().replaceAll("\n",","));
                if(StringUtils.isEmpty(blNumber)){
                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "请输入提单号", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    MainWindow.mainWindow.getButtonBill().setEnabled(true);
                    return;
                }
                //保函号码
                String letterNo= StringUtils.trim(MainWindow.mainWindow.getTextLetterNo().getText());
                if(StringUtils.isEmpty(letterNo)){
                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "请输入保函号", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    MainWindow.mainWindow.getButtonBill().setEnabled(true);
                    return;
                }
                //票据类型
                int typeIndex=MainWindow.mainWindow.getComboBillType().getSelectedIndex();
                if(typeIndex<=0){
                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "请选择票据类型", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    MainWindow.mainWindow.getButtonBill().setEnabled(true);
                    return;
                }
                MainWindow.mainWindow.getProgressBill().setIndeterminate(true);
                MainWindow.mainWindow.getProgressBill().setString("正在更新");
//                String selectSql="select EDI_NO,CollectTax,Tax_No, BLNo from Form_Head where BLNo =?";
                //update Form_Head set Tax_No='',CollectTax='' where BLNo='EUKOGBCH1542990W';
                String[] blNumbers=blNumber.split(",");
                String sql="update Form_Head set Tax_No=?,CollectTax=? where BLNo=?";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int num = 0;
                            for (int i=0;i<blNumbers.length;i++){
                                String blNo = blNumbers[i];
                                List<Object> params=new ArrayList<>();
                                params.add(letterNo);
                                params.add(String.valueOf(typeIndex));
                                params.add(blNo);

                                num += AccessDBUtil.update(sql,params);
                            }
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "更新成功"+num+"条记录", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "更新失败", "失败",
                                    JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                            logger.error(e.getMessage());
                        }finally {
                            MainWindow.mainWindow.getProgressBill().setIndeterminate(false);
                            MainWindow.mainWindow.getProgressBill().setString("更新完成");
                            MainWindow.mainWindow.getButtonBill().setEnabled(true);
                        }
                    }
                }).start();

            }
        });


        //更新关联号码
        MainWindow.mainWindow.getButtonCorrelate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.mainWindow.getButtonCorrelate().setEnabled(false);
                //提单号
                String blNumber= StringUtils.trim(MainWindow.mainWindow.getTextBlCorrelate().getText().replaceAll("\n",","));
                if(StringUtils.isEmpty(blNumber)){
                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "请输入提单号", "提示",
                            JOptionPane.WARNING_MESSAGE);
                    MainWindow.mainWindow.getButtonCorrelate().setEnabled(true);
                    return;
                }
                MainWindow.mainWindow.getProgressCorrelate().setIndeterminate(true);
                MainWindow.mainWindow.getProgressCorrelate().setString("正在更新");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String[] blNumbers=blNumber.split(",");
                            List<Map<String, Object>> resList = new ArrayList<>();
                            for (int i = 0; i < blNumbers.length ; i++){
                                //查询118报检号
                                String selectSql="select CiqDeclNo from Form_Head where BLNo =? and CiqDeclNo is not null and CiqDeclNo<>''";
                                List<Object> selectParams=new ArrayList<>();
                                selectParams.add(blNumbers[i]);
                                List<Map<String, Object>> result= AccessDBUtil.select(selectSql,selectParams);
                                if(result==null||result.size()==0){
                                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "提单号:"+blNumbers[i]+" 未查询到关联单", "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    MainWindow.mainWindow.getProgressCorrelate().setIndeterminate(false);
                                    MainWindow.mainWindow.getProgressCorrelate().setString("更新完成");
                                    return;
                                }else  if(result.size()>1){
                                    JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "提单号:"+blNumbers[i]+" 查询到多个关联单", "提示",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    MainWindow.mainWindow.getProgressCorrelate().setIndeterminate(false);
                                    MainWindow.mainWindow.getProgressCorrelate().setString("更新完成");
                                    return;
                                }else  if(result.size()==1){
                                    String correlationNo=result.get(0).get("CiqDeclNo").toString();
                                    if(StringUtils.isEmpty(correlationNo)){
                                        JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "关联单号为空", "提示",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        MainWindow.mainWindow.getProgressCorrelate().setIndeterminate(false);
                                        MainWindow.mainWindow.getProgressCorrelate().setString("更新完成");
                                        return;
                                    }else{
                                        resList.add(result.get(0));
                                    }
                                }
                            }
                            int num = 0 ;
                            for (int i = 0; i < resList.size() ; i++){
                                String correlationNo=resList.get(i).get("CiqDeclNo").toString();
                                String updateSql="update Form_Head set CorrelationNo=?,CorrelationReasonFlag='10' " +
                                        " where BLNo=? and (CiqDeclNo is null or CiqDeclNo='')" +
                                        " and (CorrelationNo is null or CorrelationNo='')";
                                List<Object> params=new ArrayList<>();
                                params.add(correlationNo);
                                params.add(blNumbers[i]);
                                num += AccessDBUtil.update(updateSql,params);
                            }
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "更新成功"+num+"条记录", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getSettingPanel(), "更新失败", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                            logger.error(ex.getMessage());
                            ex.printStackTrace();
                        }finally {
                            MainWindow.mainWindow.getProgressCorrelate().setIndeterminate(false);
                            MainWindow.mainWindow.getProgressCorrelate().setString("更新完成");
                            MainWindow.mainWindow.getButtonCorrelate().setEnabled(true);
                        }
                    }
                }).start();

            }
        });
    }


}
