package com.tilchina.edi.ui.listener;

import com.tilchina.edi.ui.MainWindow;
import com.tilchina.edi.ui.bean.*;
import com.tilchina.edi.util.AccessDBUtil;
import com.tilchina.edi.util.LoggerUtil;
import com.tilchina.edi.util.excel.DecryptUtil;
import com.tilchina.edi.util.excel.ExcelExport;
import com.tilchina.edi.util.excel.ExcelImport;
import com.tilchina.edi.util.excel.annotaion.ExcelField;
import com.tilchina.edi.util.lang.DateUtils;
import com.tilchina.edi.util.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class ExcelListener {
    private static final Logger logger= LoggerUtil.getLogger(ExcelListener.class);
    //导入的车辆证明书集合
    private static List<CarInfo> carInfoList = null;
    public static void addListener(){
        //浏览文件
        MainWindow.mainWindow.getFileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File beforeFile=new File(MainWindow.mainWindow.getFileTextField().getText());
                JFileChooser fileChooser;

                if(beforeFile.exists()){
                    fileChooser=new JFileChooser(beforeFile);
                }else{
                    fileChooser=new JFileChooser();
                }

                FileFilter filter=new FileNameExtensionFilter("*.xlsx,*.xls","xlsx","xls");
                fileChooser.setFileFilter(filter);

                int approve=fileChooser.showOpenDialog(MainWindow.mainWindow.getSettingPanel());
                if(approve==JFileChooser.APPROVE_OPTION){
                    MainWindow.mainWindow.getFileTextField().setText(fileChooser.getSelectedFile().getAbsolutePath());
                }

            }
        });
        //导入文件
        MainWindow.mainWindow.getImportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.mainWindow.getImportButton().setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            if (StringUtils.isEmpty(MainWindow.mainWindow.getFileTextField().getText())){
                                JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), "文件路径不能为空", "提示",
                                        JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            int importIndex=MainWindow.mainWindow.getImportComboBox().getSelectedIndex();
                            if (importIndex<1){
                                JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), "导入类型不能为空", "提示",
                                        JOptionPane.WARNING_MESSAGE);
                                return;
                            }
//                            MainWindow.mainWindow.getTotalProgressBar().setValue(0);
                            MainWindow.mainWindow.getTotalProgressBar().setString("处理中...");
                            MainWindow.mainWindow.getTotalProgressBar().setIndeterminate(true);
                            File file=new File(MainWindow.mainWindow.getFileTextField().getText());
                            ExcelImport ei=new ExcelImport(file,1,0);
                            String resMessage = "导入完成！";
                            switch (importIndex){
                                case 1:
                                    List<CarInfo> carInfosList=ei.getDataList(CarInfo.class);
                                    resMessage = importCatInfo(carInfosList);
                                    break;
                                case 2:
                                    List<CorrelationNumber> list=ei.getDataList(CorrelationNumber.class);
                                    importCorrelation(list);
                                    break;
                                case 3:
                                    break;
                                default:
                                    break;
                            }

//                            MainWindow.mainWindow.getTotalProgressBar().setMaximum(100);
//                            MainWindow.mainWindow.getTotalProgressBar().setValue(100);
                            MainWindow.mainWindow.getTotalProgressBar().setString("处理完成");
                            MainWindow.mainWindow.getTotalProgressBar().setIndeterminate(false);
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), resMessage , "完成!",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception e1) {
                            logger.error(e1.getMessage());
                            e1.printStackTrace();
                        }finally {
//                            MainWindow.mainWindow.getTotalProgressBar().setMaximum(100);
//                            MainWindow.mainWindow.getTotalProgressBar().setValue(100);
                            MainWindow.mainWindow.getProgressDb().setString("处理完成");
                            MainWindow.mainWindow.getTotalProgressBar().setIndeterminate(false);
                            MainWindow.mainWindow.getImportButton().setEnabled(true);
                        }
                    }
                }).start();

            }
        });

        //导出文件
        MainWindow.mainWindow.getExportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.mainWindow.getExportButton().setEnabled(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            //String text= MainWindow.mainWindow.getNoTextArea().getText();
                            String text1=MainWindow.mainWindow.getNoTextArea().getText().replaceAll("\n",",");
                            //MainWindow.mainWindow.getNoTextArea().setText(text1);
                            int importIndex=MainWindow.mainWindow.getExportComboBox().getSelectedIndex();
                            if (importIndex<1){
                                JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), "导出类型不能为空", "提示",
                                        JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            //MainWindow.mainWindow.getTotalProgressBar().setValue(0);
                            MainWindow.mainWindow.getTotalProgressBar().setIndeterminate(true);
                            MainWindow.mainWindow.getTotalProgressBar().setString("正在处理");
                            String resMessage = "导出完成！";
                            switch (importIndex){
                                case 1:
                                    if(StringUtils.isBlank(text1)){
                                        JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), "EDI号不能为空！", "提示",
                                                JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    String[] edis= text1.split(",");
                                    resMessage = exportBgData(edis);
                                    break;
                                case 2:
                                    //String[] edis= MainWindow.mainWindow.getNoTextArea().getText().split("\n");
                                    resMessage = exportCarInfoData();
                                    break;
                                case 3:
                                    if(StringUtils.isEmpty(text1)){
                                        JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), "EDI号不能为空！", "提示",
                                                JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    String[] numbers= text1.split(",");
                                    resMessage = exportCorralation(numbers);
                                    break;
                                default:
                                    break;
                            }
                            MainWindow.mainWindow.getTotalProgressBar().setIndeterminate(false);
                            MainWindow.mainWindow.getTotalProgressBar().setString("处理完成");
                            JOptionPane.showMessageDialog(MainWindow.mainWindow.getExcelPanel(), resMessage , "完成!",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }catch (Exception e1) {
                            logger.error(e1.getMessage());
                            e1.printStackTrace();
                        }finally {
                            MainWindow.mainWindow.getTotalProgressBar().setIndeterminate(false);
                            MainWindow.mainWindow.getTotalProgressBar().setString("处理完成");
                            MainWindow.mainWindow.getExportButton().setEnabled(true);
                        }
                    }
                }).start();
            }
        });
    }

    //导入关联号码文件
    private static void importCorrelation(List<CorrelationNumber> list){
        if(list!=null&&list.size()>0){
            int currentImported = 1;
            for(CorrelationNumber item:list){
//                MainWindow.mainWindow.getTotalProgressBar().setValue(currentImported/list.size()*100);
                String sql="update Form_Head set CorrelationNo = ? , CorrelationReasonFlag = '10' where EDI_NO= ?";
                List<Object> params=new ArrayList<>();
                params.add(item.getCiqNo());
                params.add(item.getEdiNo());
                try {
                    AccessDBUtil.update(sql,params);
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
                currentImported++;
            }
        }
    }
    //导出报检号码
    private static String exportCorralation(String[] edis){
        if(edis!=null){
            List<CorrelationNumber> list=new ArrayList<>();
            String selectSql="select EDI_NO,customs_id,CiqDeclNo from  Form_Head where EDI_NO=? ";
            for(int i=0;i<edis.length;i++){
                List<Object> params=new ArrayList<>();
                params.add(edis[i]);
                try {
                    List<Map<String, Object>> result= AccessDBUtil.select(selectSql,params);
                    if(result!=null&&result.size()!=0){
                        CorrelationNumber item=new CorrelationNumber();
                        item.setEdiNo(result.get(0).get("EDI_NO").toString());
                        item.setCustomsNo(result.get(0).get("customs_id").toString());
                        item.setCiqNo(result.get(0).get("CiqDeclNo").toString());
                        list.add(item);
                    }else{
                        return "EDI："+edis[i]+" 未找到相关数据！";
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            String fileName="C://"+"报检号码导出"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            ExcelExport ee=new ExcelExport("报检号码",null,CorrelationNumber.class, ExcelField.Type.EXPORT,null);
            ee.setDataList(list);
//            Workbook wb=ee.getWorkbook();
//            ExcelExport ee1=new ExcelExport(wb,"关联号码1",null,CorrelationNumber.class, ExcelField.Type.EXPORT,null);
//            ee1.setDataList(list);

            try {
                ee.writeFile(fileName);
//                ee1.writeFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                ee.dispose();
                //ee1.dispose();
            }
        }
        return "导出完成!";
    }
    /***
     * 根据EntryId和Gno查询FormList数据
     * @param decFormList 参数
     * @return resultBean 查询到的数据
     */
    private static DecFormList findByEntryIdAndGno(DecFormList decFormList) {
        DecFormList resultBean = new DecFormList();
        try {
            String sql = "SELECT pre_entry_id,g_no,g_name,g_model,qty_1 from Form_List WHERE pre_entry_id = ? and g_no = ? ";
            List<Object> params=new ArrayList<>();
            params.add(decFormList.getPreentryid());
            params.add(decFormList.getGNo()-1);

            List<Map<String, Object>> result = AccessDBUtil.select(sql, params);
            DecimalFormat  df = new DecimalFormat("##");
            if(result!=null && result.size()!=0){
                Map<String, Object> map = result.get(0);
                resultBean.setGoodsModel(objToString(map.get("g_model")));
                resultBean.setGoodsName(objToString(map.get("g_name")));
                resultBean.setPreentryid(objToString(map.get("pre_entry_id")));
                //resultBean.setQty1(null == map.get("qty_1")?0:Integer.parseInt(df.format(map.get("qty_1"))));
            }else{
                logger.info(sql);
                return null;
            }
        } catch (Exception e) {
            logger.info("根据EntryId和Gno查询FormList数据失败!");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultBean;
    }
    private static String objToString(Object obj){ //Obj 转 String
        String resultStr = "";
        if(null!=obj){
            resultStr = obj.toString();
        }
        return resultStr;
    }
    private static Integer objToInteger(Object obj){//Obj转 Integer
        Integer resultInt = 0;
        try {
            if(null!=obj){
                resultInt = Integer.parseInt(obj.toString());
            }
            return resultInt;
        }catch (Exception e){
            logger.info("数据：" + obj.toString()+" 转Integer失败！"+e.getMessage());
            return resultInt;
        }
    }
    private static Double objToDouble(Object obj){// Obj 转double
        Double resultStr = null;
        try {
            if(null!=obj){
                resultStr = Double.valueOf(obj.toString());
            }
            return resultStr;
        }catch (Exception e){
            logger.info("数据："+obj.toString()+" 转Double失败！"+e.getMessage());
            return null;
        }
    }
    //导入车辆证明书文件
    private static String  importCatInfo(List<CarInfo> list){
        DecFormList decFormList = new DecFormList();
        DecryptUtil util =  DecryptUtil.getInstance();
        int i = 1;
        for (CarInfo carInfo:list ) {
//            MainWindow.mainWindow.getTotalProgressBar().setValue(i/list.size()*100);
            //设置参数  entryId gno  去form LIst 中查询gmodel 和 gname
            decFormList.setPreentryid(carInfo.getEnterId());
            decFormList.setGNo(Integer.parseInt(carInfo.getGNo()));
            DecFormList resultBean = findByEntryIdAndGno(decFormList);
            if (null == resultBean){
                return "导入失败！ EntryId:"+carInfo.getEnterId()+" GNo:"+carInfo.getGNo()+" 未找到相关数据！";
            }
            //将查询的结果 解密设置到车辆证明书中
            carInfo.setGModel(util.decrypt(resultBean.getGoodsModel()));
            carInfo.setGName(util.decrypt(resultBean.getGoodsName()));
            carInfo.setOperType("D");
            carInfo.setClientSeq(UUID.randomUUID().toString().replaceAll("-", "").substring(0,18));//uuid 自己随便生成
            carInfo.setStatus("5");
            carInfo.setCarType("H");
            i++;
        }
        carInfoList = list;

        if(list!=null&&list.size()>0){
            CarInfo carInfo = list.get(0);
            String querySql = "SELECT carcoverno from cic_data WHERE carcoverno = ?";
            List<Object> paras=new ArrayList<>();
            paras.add(carInfo.getCarCoverNo());
            try {
                List<Map<String, Object>> maps = AccessDBUtil.select(querySql, paras);
                if(maps!=null&&maps.size()!=0){
                    return "车辆证明书已存在！";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int currentImported = 1;
            for(CarInfo item:list){
//                MainWindow.mainWindow.getTotalProgressBar().setValue(currentImported/list.size()*100);
                String sql="insert into CIC_Data (opertype,clientseqno,entryid,gno,color,carcoverno,energytype,engineno,qtyexhaust,elecmotorno,elecmotorpower,manufacturedate,cartype,g_name,g_model,status)"+
                        " values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?)";
                List<Object> params=new ArrayList<>();
                params.add(item.getOperType());
                params.add(item.getClientSeq());//uuid 自己随便生成
                params.add(item.getEnterId());
                params.add(item.getGNo());
                params.add(item.getColor());

                params.add(item.getCarCoverNo());
                params.add(item.getEnergyType());
                params.add(item.getEngineNo());
                params.add(item.getQtyExhaust());
                params.add(item.getElecMotorNo());
                params.add(item.getElecMotorPower());
                params.add(item.getManufactureDate());

                params.add(item.getCarType());
                params.add(item.getGName());
                params.add(item.getGModel());
                params.add(item.getStatus());

                try {
                    AccessDBUtil.update(sql,params);
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
                currentImported++;
            }
            return "导入完成！";
        }else{
            return "无数据，请先导入！";
        }
    }
    //车辆证明书的导出
    private static String exportCarInfoData(){
        if (null == carInfoList || carInfoList.size()==0){
            return "车辆证明书为空，请先导入数据！";
        }
        String fileName="C://"+"车辆证明书导出"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        ExcelExport ee = new ExcelExport("sheet1",null,CarInfo.class, ExcelField.Type.EXPORT,null);
        ee.setDataList(carInfoList);
        try {
            ee.writeFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ee.dispose();
        }
        return "导出完成！";
    }
    //导出报关数据
    private static String  exportBgData(String[] edis){
        if(edis!=null){
            List<DecFormHead> CopEdiList = new ArrayList<>();
            for(int i=0;i<edis.length;i++){
                String findCopNoSql = "select edi_no,cop_no from form_head where edi_no = ?";
                List<Map<String, Object>> ediAndCopList =  findDataByEntryId(edis[i],findCopNoSql);
                if(ediAndCopList!=null&&ediAndCopList.size()!=0){
                    DecFormHead formHead = new DecFormHead();
                    formHead.setEdiNo(objToString(ediAndCopList.get(0).get("EDI_NO")));
                    formHead.setCopNo(objToString(ediAndCopList.get(0).get("COP_NO")));
                    CopEdiList.add(formHead);
                }else{
                    return "EDI编号:" + edis[i] + " 未找到相关数据，请核对！";
                }
            }
            CopEdiList.sort(new Comparator<DecFormHead>() {
                @Override
                public int compare(DecFormHead o1, DecFormHead o2) {
                    return  o1.getCopNo().compareTo(o2.getCopNo());
                }
            });
            List<DecFormHead> headList = new ArrayList<>();
            List<DecFormList> listList = new ArrayList<>();
            List<DecGoodsLimit> goodsLimitsList = new ArrayList<>();
            List<DecGoodsLimitVin> goodsLimitVinList = new ArrayList<>();
            DecryptUtil decryptUtil = DecryptUtil.getInstance();

            for (int i=0;i<CopEdiList.size();i++){
                //获取edi号
                String ediNo = CopEdiList.get(i).getEdiNo();

                //根据edi 号 查询 form head 的数据
                DecFormHead formHead = findHeadListByEdiNo(ediNo);
                if(null == formHead){
                    return "EDI号："+ediNo+" 未找到相关数据！";
                }
                formHead.setHeadId(i+1);
                //对加密字段进行解密
                formHead.setAgentCode(decryptUtil.decrypt(formHead.getAgentCode()));//申报单位代码
                formHead.setAgentName(decryptUtil.decrypt(formHead.getAgentName()));//申报单位名称
                formHead.setTransportModeCode(decryptUtil.decrypt(formHead.getTransportModeCode()));//运输方式
                formHead.setTrafName(decryptUtil.decrypt(formHead.getTrafName()));//运输工具代码及名称
                formHead.setContrNo(decryptUtil.decrypt(formHead.getContrNo()));//合同号
                formHead.setOwnerCode(decryptUtil.decrypt(formHead.getOwnerCode()));//货主单位代码
                formHead.setOwnerName(decryptUtil.decrypt(formHead.getOwnerName()));//货主单位名称
                String preEntryNo = formHead.getPreEntryNo();//获取entryId
                //根据entryId 查询 单证代码 编号 Cert_List
                String certSql = "SELECT pre_entry_id,docu_code,cert_code from Cert_List WHERE pre_entry_id = ?";
                List<Map<String, Object>> certList = findDataByEntryId(preEntryNo,certSql);
                if(null != certList){
                    formHead.setCertCode1(objToString(certList.get(0).get("docu_code")));//1单证代码
                    formHead.setCertNo1(objToString(certList.get(0).get("cert_code")));//2单证编号
                }
                //根据entry Id 查询  包装材料种类 DecOtherPack
                String packSql = "SELECT PreEntryId,PackQty,PackType from DecOtherPack WHERE PreEntryId = ?";
                List<Map<String, Object>> packList =  findDataByEntryId(preEntryNo,packSql);
                if (null != packList){
                    //formHead.setPackNo("1");//1序号
                    formHead.setPackQty(objToString(packList.get(0).get("PackQty")));//2包装件数
                    formHead.setPackType(objToString(packList.get(0).get("PackType")));//3包装材料种类
                }
                //根据entry Id 查询 联系人
                String userSql = "SELECT PreEntryId,UseOrgPersonCode,UseOrgPersonTel from DecUser WHERE PreEntryId = ?";
                List<Map<String, Object>> userList =  findDataByEntryId(preEntryNo,userSql);
                if(null!= userList){
                    formHead.setUseOrgPersonCode(objToString(userList.get(0).get("UseOrgPersonCode")));//2使用单位联系人
                    formHead.setUseOrgPersonTel(objToString(userList.get(0).get("UseOrgPersonTel")));//3使用单位联系电话
                }
                // 根据 entryId 查询DecRequestCert 申请单证 数据
                String requestCertSql = "SELECT PreEntryId,AppCertCode,ApplOri,ApplCopyQuan from DecRequestCert WHERE PreEntryId = ?";
                List<Map<String, Object>> requestCertList =  findDataByEntryId(preEntryNo,requestCertSql);
                if (null != requestCertList){
                    formHead.setInvoicesType(objToString(requestCertList.get(0).get("AppCertCode")));//单据类型
                    formHead.setAppCertCode(objToString(requestCertList.get(0).get("AppCertCode")));//2申请单证代码
                    formHead.setAppCertQriQty(objToInteger(requestCertList.get(0).get("ApplOri")));//3申请单证正本数
                    formHead.setAppCertCopyQty(objToInteger(requestCertList.get(0).get("ApplCopyQuan")));//4申请单证副本数
                }
                //根据 entryId 查询 DecMod_EdoRelation 电子随附单据关联关系 表的数据
                String edoRelationSql = "SELECT ENTRY_ID,FILE_TYPE,FILE_NAME,EDOCOWNERCODE,EDOCOWNERNAME,OPNOTE from DecMod_EdoRelation WHERE ENTRY_ID = ?";
                List<Map<String, Object>> edoRelationList =  findDataByEntryId(preEntryNo,edoRelationSql);
                if(null != edoRelationList){
                    formHead.setInvoicesType(objToString(edoRelationList.get(0).get("FILE_TYPE")));//单据类型
                    formHead.setEdocOwnerCode(objToString(edoRelationList.get(0).get("EDOCOWNERCODE")));//3所属单位代码
                    formHead.setEdocOwnerName(objToString(edoRelationList.get(0).get("EDOCOWNERNAME")));//4所属单位名称
                }
                headList.add(formHead);

                //根据 preEntryId 查询 formList数据
                List<DecFormList> tempListList = findFormListByEntryId(preEntryNo ,formHead.getHeadId() );
                //对formList 的数据进行排序 根据gNo
                tempListList.sort(new Comparator<DecFormList>() {
                    @Override
                    public int compare(DecFormList formList1, DecFormList formList2) {
                        return formList1.getGNo().compareTo(formList2.getGNo());
                    }
                });
                listList.addAll(tempListList);
                //根据 preEntryId 查询 goodslimit 表中数据
                String goodsLimitSql = "SELECT PreEntryId,GoodsNo,OrderNo,LicTypeCode,LicenceNo,LicWrtofDetailNo,LicWrtofQty from DecGoodsLimit WHERE PreEntryId = ?";
                List<Map<String, Object>> tempGoodsLimitList =  findDataByEntryId(preEntryNo,goodsLimitSql);
                if(null != tempGoodsLimitList){
                    for (Map<String, Object> resMap:tempGoodsLimitList ) {
                        DecGoodsLimit goodsLimit = new DecGoodsLimit();
                        goodsLimit.setHeadId(formHead.getHeadId());//headId 表头编号
                        goodsLimit.setGNo(objToInteger(resMap.get("GoodsNo")));//2.表体编号
                        goodsLimit.setLicenseInfoNo(objToString(resMap.get("OrderNo")));//2许可证信息序号
                        goodsLimit.setLicenseTypeCode(objToString(resMap.get("LicTypeCode")));//3许可证类别代码
                        goodsLimit.setLicenseNo(objToString(resMap.get("LicenceNo")));//4许可证编号
                        goodsLimit.setLicenseDetailNo(objToString(resMap.get("LicWrtofDetailNo")));//5许可证核销明细序号
                        goodsLimit.setLicenseQty(objToString(resMap.get("LicWrtofQty")));//6许可证核销数量
                        goodsLimitsList.add(goodsLimit);
                    }
                }
                //根据 preEntryId 查询 goodslimit vin 表中数据
                String goodsLimitVinSql = "SELECT * from DecGoodsLimitVin WHERE PreEntryId = ?";
                List<Map<String, Object>> tempGoodsLimitsVinList =  findDataByEntryId(preEntryNo,goodsLimitVinSql);
                if(null != tempGoodsLimitsVinList){
                    for (Map<String, Object> resMap:tempGoodsLimitsVinList ) {
                        DecGoodsLimitVin goodsLimitVin = new DecGoodsLimitVin();
                        goodsLimitVin.setHeadId(formHead.getHeadId());//headId 表头编号1
                        goodsLimitVin.setGNo(objToInteger(resMap.get("OrderNo")));//2表体编号
                        goodsLimitVin.setLicenseInfoNo("1");//3许可证信息序号
                        goodsLimitVin.setLicenseNo(objToString(resMap.get("LicenceNo")));//4许可证编号
                        goodsLimitVin.setLicenseTypeCode(objToString(resMap.get("LicTypeCode")));//4许可证类别代码
                        goodsLimitVin.setVinNo(objToString(resMap.get("VinNo")));//5VIN序号
                        goodsLimitVin.setWaybillDate(objToString(resMap.get("BillLadDate")));//6提/运单日期
                        goodsLimitVin.setQualityQgp(objToString(resMap.get("QualityQgp")));//7质量保质期
                        goodsLimitVin.setMotorNo(objToString(resMap.get("MotorNo")));//8发动机号或电机号
                        goodsLimitVin.setVinCode(objToString(resMap.get("VinCode")));//9车辆识别代码（VIN）
                        goodsLimitVin.setVin(objToString(resMap.get("ChassisNo")));//10底盘(车架)号
                        goodsLimitVin.setInvoiceQty(objToString(resMap.get("InvoiceNum")));//11发票所列数量
                        goodsLimitVin.setDescriptionCn(objToString(resMap.get("ProdCnnm")));//12品名（中文名称）
                        goodsLimitVin.setDescriptionEng(objToString(resMap.get("ProdEnnm")));//13品名（英文名称）
                        goodsLimitVin.setType(objToString(resMap.get("ModelEn")));//14型号（英文）
                        goodsLimitVin.setUnitPrice(objToDouble(resMap.get("PricePerUnit")));//15单价
                        goodsLimitVin.setInvoiceNo(objToString(resMap.get("InvoiceNo")));//16发票号

                        goodsLimitVinList.add(goodsLimitVin);
                    }
                }

            }
            //对 许可证 信息 进行排序
            goodsLimitsList.sort(new Comparator<DecGoodsLimit>() {
                @Override
                public int compare(DecGoodsLimit goodsLimit1, DecGoodsLimit goodsLimit2) {
                    int i = goodsLimit1.getHeadId().compareTo(goodsLimit2.getHeadId());
                    return i == 0?goodsLimit1.getGNo().compareTo(goodsLimit2.getGNo()):i;
                }
            });

            //对许可证 vin信息 进行排序
            goodsLimitVinList.sort(new Comparator<DecGoodsLimitVin>() {
                @Override
                public int compare(DecGoodsLimitVin goodsLimitVin1, DecGoodsLimitVin goodsLimitVin2) {
                    int i = goodsLimitVin1.getHeadId().compareTo(goodsLimitVin2.getHeadId());
                    return i == 0 ? goodsLimitVin1.getGNo().compareTo(goodsLimitVin2.getGNo()) : i;
                }
            });
            String fileName="C://"+"报关数据导出"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            ExcelExport ee=new ExcelExport("报关表头",null,DecFormHead.class, ExcelField.Type.EXPORT,null);
            ee.setDataList(headList);
            Workbook wb=ee.getWorkbook();
            ExcelExport ee1=new ExcelExport(wb,"报关表体",null,DecFormList.class, ExcelField.Type.EXPORT,null);
            ee1.setDataList(listList);
            ExcelExport ee2=new ExcelExport(wb,"许可证信息",null,DecGoodsLimit.class, ExcelField.Type.EXPORT,null);
            ee2.setDataList(goodsLimitsList);
            ExcelExport ee3=new ExcelExport(wb,"许可证VIN信息",null,DecGoodsLimitVin.class, ExcelField.Type.EXPORT,null);
            ee3.setDataList(goodsLimitVinList);

            try {
                ee.writeFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                ee3.dispose();
            }
        }
        return "导出完成！";
    }

    private static List<DecFormList> findFormListByEntryId(String preEntryNo,Integer headId) {
        String sql = "SELECT *  from form_list WHERE pre_ENTRY_ID = ?";
		DecryptUtil decryptUtil = DecryptUtil.getInstance();
        List<Object> params = new ArrayList<>();
        params.add(preEntryNo);
        List<DecFormList> result = new ArrayList<>();
        try {
            List<Map<String,Object>> rs = AccessDBUtil.select(sql, params);
            if(null == rs || rs.size()==0 ){
                return null;
            }
            for (Map<String,Object> resMap : rs ) {
                DecFormList bean = new DecFormList();
                bean.setHeadId(headId);
                bean.setPreentryid(objToString(resMap.get("pre_entry_id")));
                bean.setGNo(objToInteger(resMap.get("g_no"))+1); //1序号
                bean.setContrItem(objToString(resMap.get("contr_item")));//2新贸序号
                bean.setCustomCode(decryptUtil.decrypt(objToString(resMap.get("code_t"))));//3商品编号
                bean.setAppendCode(decryptUtil.decrypt(objToString(resMap.get("code_s"))));//4附加编号
                bean.setGoodsName(decryptUtil.decrypt(objToString(resMap.get("g_name"))));//5商品名称
                bean.setSpec(decryptUtil.decrypt(objToString(resMap.get("g_model"))));//6商品规格型号
                bean.setCountryOfOriginName(decryptUtil.decrypt(objToString(resMap.get("origin_country"))));//7原产国/目的国
                bean.setDeclarationQty(objToDouble(resMap.get("qty_conv")));//8申报数量
                bean.setDeclValuationUnit(objToString(resMap.get("g_unit")));//9申报计量单位
                bean.setLegalQty1(objToDouble(resMap.get("qty_1")));//10法定数量
                bean.setLegalValuationUnit1(objToString(resMap.get("unit_1")));//11法定计量单位(第一计量单位)
                bean.setLegalQty2(objToDouble(resMap.get("qty_2")));//12第二数量
                bean.setLegalValuationUnit2(objToString(resMap.get("unit_2")));//13第二计量单位
                bean.setCurrencyCode(objToString(resMap.get("trade_curr")));//14成交币制
                bean.setUnitPrice(objToDouble(resMap.get("decl_price")));//15申报单价
                bean.setAmountPrice(objToDouble(resMap.get("trade_total")));//16成交总价
                bean.setNcDetailCode(objToString(resMap.get("duty_mode")));//17征减免税方式
                bean.setPurposeCode(objToString(resMap.get("use_to")));//18用途
                bean.setGoodsVersion(objToString(resMap.get("prdt_no")));//19加工成品版本号
                //bean.setGoodsId(objToString(resMap.get("")));//20加工料件/成品货号************未找到
                //bean.setGrossWT(objToString(resMap.get("")));//21毛重************未找到
                //bean.setNetWT(objToString(resMap.get("")));//22净重************未找到
                bean.setWorkUsd(objToString(resMap.get("work_usd")));//23工缴费
                bean.setCountryOfDestCode(objToString(resMap.get("DESTINATION_COUNTRY")));//24原产国/目的国

                bean.setDistrictCode(objToString(resMap.get("DistrictCode")));//25境内目的地/境内货源地
                bean.setOriginCountryStd(objToString(resMap.get("OriginCountryStd")));//26原产国/目的国
                bean.setDestinationCountryStd(objToString(resMap.get("DestinationCountryStd")));//27原产国/目的国
                bean.setTradeCurrStd(objToString(resMap.get("TradeCurrStd")));//28成交币制
                bean.setGUnitStd(objToString(resMap.get("GUnitStd")));//29申报计量单位
                bean.setFirstUnitStd(objToString(resMap.get("FirstUnitStd")));//30法定计量单位(第一计量单位)
                bean.setSecondUnitStd(objToString(resMap.get("SecondUnitStd")));//31第二计量单位
                bean.setCiqCode(objToString(resMap.get("CiqCode")));//32检验检疫编码
                bean.setDeclGoodsEname(objToString(resMap.get("DeclGoodsEname")));//33商品英文名称
                bean.setOrigPlaceCode(objToString(resMap.get("OrigPlaceCode")));//34原产地区代码
                bean.setPurpose(objToString(resMap.get("Purpose")));//35用途代码
                bean.setProdValidDt(objToString(resMap.get("ProdValidDt")));//36产品有效期
                bean.setProdQgp(objToString(resMap.get("ProdQgp")));//37产品保质期
                bean.setGoodsAttr(objToString(resMap.get("GoodsAttr")));//38货物属性代码
                bean.setStuff(objToString(resMap.get("Stuff")));//39成份/原料/组份
                bean.setUnCode(objToString(resMap.get("UnCode")));//40UN编码
                bean.setDangName(objToString(resMap.get("DangName")));//41危险货物名称
                bean.setDangPackType(objToString(resMap.get("DangPackType")));//42危包类别
                bean.setDangPackSpec(objToString(resMap.get("DangPackSpec")));//43危包规格
                bean.setEngManEntCnm(objToString(resMap.get("EngManEntCnm")));//44境外生产企业名称
                bean.setNoDangFlag(objToString(resMap.get("NoDangFlag")));//45非危险化学品
                bean.setDestCode(objToString(resMap.get("DestCode")));//46目的地代码
                bean.setGoodsSpec(objToString(resMap.get("GoodsSpec")));//47检验检疫货物规格
                bean.setGoodsModel(objToString(resMap.get("GoodsModel")));//48货物型号
                bean.setGoodsBrand(objToString(resMap.get("GoodsBrand")));//49货物品牌
                bean.setProduceDate(objToString(resMap.get("ProduceDate")));//50生产日期
                bean.setProdBatchNo(objToString(resMap.get("ProdBatchNo")));//51生产批号
                bean.setCiqName(objToString(resMap.get("CiqName")));//52检验检疫名称
                bean.setMnufctrRegNo(objToString(resMap.get("MnufctrRegno")));//53生产单位注册号
                bean.setMnufctrRegName(objToString(resMap.get("MnufctrRegName")));//54生产单位名称
                result.add(bean);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  根据preEntryNo  SQL语句 查询 相关数据库中的数据
     * @param preEntryNo
     * @param sql
     * @return
     */
    private static List<Map<String,Object>> findDataByEntryId(String preEntryNo , String sql) {
        List<Object> params = new ArrayList<>();
        params.add(preEntryNo);
        List<Map<String, Object>> result = null;
        try {
            result = AccessDBUtil.select(sql, params);
            if(null == result || result.size()==0 ){
                return null;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     *  根据edi 查询formHead 表数据
     * @param edi
     * @return
     */
    private static DecFormHead findHeadListByEdiNo(String edi) {
        String sql = "select * from Form_Head where EDI_NO = ?";
        List<Object> params=new ArrayList<>();
        params.add(edi);
        DecFormHead resBean =new DecFormHead();
        try {
            List<Map<String, Object>> result= AccessDBUtil.select(sql,params);
            if(result!=null && result.size()!=0){
                resBean.setPreEntryNo(objToString(result.get(0).get("pre_entry_id")));//1预录入编号
                resBean.setProprietorCompanyCode(objToString(result.get(0).get("trade_co")));//2收发货人编号
                resBean.setProprietorCompanyName(objToString(result.get(0).get("trade_name")));//3收发货人名称
                resBean.setDistrictCode(objToString(result.get(0).get("district_code")));//4货主单位地区代码
                resBean.setOwnerCode(objToString(result.get(0).get("owner_code")));//5货主单位代码
                resBean.setOwnerName(objToString(result.get(0).get("owner_name")));//6货主单位名称
                resBean.setAgentCode(objToString(result.get(0).get("agent_code")));//7申报单位代码
                resBean.setAgentName(objToString(result.get(0).get("agent_name")));//8申报单位名称
                resBean.setTransportModeCode(objToString(result.get(0).get("traf_mode")));//9运输方式
                resBean.setIePort(objToString(result.get(0).get("i_e_port")));//10进出口岸
                resBean.setDistinatePortCode(objToString(result.get(0).get("distinate_port")));//11指运港
                resBean.setTrafName(objToString(result.get(0).get("traf_name")));//12运输工具代码及名称
                resBean.setContrNo(objToString(result.get(0).get("contr_no")));//13合同号
                resBean.setInRatio(objToString(result.get(0).get("in_ratio")));//14内销比率
                resBean.setBillNo(objToString(result.get(0).get("bill_no")));//15提单或运单号
                resBean.setCountryOfOdCode(objToString(result.get(0).get("trade_country")));//16贸易国（地区）
                resBean.setTradeMethodCode(objToString(result.get(0).get("trade_mode")));//17监管方式

                resBean.setNcCode(objToString(result.get(0).get("cut_mode")));//18征免性质
                //resBean.setPayMode(objToString(result.get(0).get("")));//19纳税方式*************未找到对应
                resBean.setTermsOfDeliveryCode(objToString(result.get(0).get("trans_mode")));//20成交方式
                resBean.setPayWay(objToString(result.get(0).get("pay_way")));//21收结汇方式
                resBean.setFeeMark(objToString(result.get(0).get("fee_mark")));//22运费标记
                resBean.setFeeCurrCode(objToString(result.get(0).get("fee_curr")));//23运费币制
                resBean.setFeeRate(objToDouble(result.get(0).get("fee_rate")));//24运费/率
                resBean.setOtherMark(objToString(result.get(0).get("other_mark")));//25杂费标记
                resBean.setOtherCurrCode(objToString(result.get(0).get("other_curr")));//26杂费币制
                resBean.setOtherRate(objToDouble(result.get(0).get("other_rate")));//27杂费/率
                resBean.setInsurMark(objToString(result.get(0).get("insur_mark")));//28保险费标记
                resBean.setInsurCurrCode(objToString(result.get(0).get("insur_curr")));//29保险费币制
                resBean.setInsurRate(objToDouble(result.get(0).get("insur_rate")));//30保险费/率
                resBean.setTotalPiece(objToInteger(result.get(0).get("pack_no")));//31件数
                resBean.setTotalGrossWeight(objToDouble(result.get(0).get("gross_wt")));//32毛重
                resBean.setTotalNetWeight(objToDouble(result.get(0).get("net_wt")));//33净重
                resBean.setLicenceNo(objToString(result.get(0).get("lisence_no")));//34许可证编号
                resBean.setApprNo(objToString(result.get(0).get("appr_no")));//35批准文号
                resBean.setPoRecordsNo(objToString(result.get(0).get("manual_no")));//36备案号
                resBean.setDateOfIe(objToString(result.get(0).get("i_e_date")));//37进出日期
                resBean.setWrapType(objToString(result.get(0).get("wrap_type")));//38包装种类
                resBean.setLabelRemark(objToString(result.get(0).get("note_s")));//39备注
                resBean.setDateOfDeclaration(objToString(result.get(0).get("d_date")));//40申报日期
                resBean.setExSource(objToString(result.get(0).get("ex_source")));//41外汇来源
                resBean.setVoyageNo(objToString(result.get(0).get("voyage_no")));//42航次号
                resBean.setIeFlag(objToString(result.get(0).get("ie_flag")));//43进出口标志
                resBean.setPrdtid(objToString(result.get(0).get("PrdtID")));//44货场代码
                resBean.setStoreNo(objToString(result.get(0).get("StoreNo")));//45保税/监管场所
                resBean.setRamanualNo(objToString(result.get(0).get("RaManualNo")));//46关联备案号
                resBean.setRadeclNo(objToString(result.get(0).get("RaDeclNo")));//47关联报关单号
                //48
                resBean.setStatus(objToString(result.get(0).get("is_status")));//49报文状态*********未找到对应
                //resBean.setMemo(objToString(result.get(0).get("")));//50备注*********未找到对应
                resBean.setTradeCoScc(objToString(result.get(0).get("TRADE_CO_SCC")));//51经营单位统一编码
                resBean.setAgentCodeScc(objToString(result.get(0).get("AGENT_CODE_SCC")));//52申报代码统一编码
                resBean.setOwnerCodeScc(objToString(result.get(0).get("OWNER_CODE_SCC")));//53货主单位统一编码
                resBean.setTradeCountryCode(objToString(result.get(0).get("TRADE_AREA_CODE")));//54贸易国别
                resBean.setPortOfIeCode(objToString(result.get(0).get("DECL_PORT")));//55申报地海关
                resBean.setEdiNo(objToString(result.get(0).get("EDI_NO")));//56EDI单据编号
                resBean.setRprFlag(objToString(result.get(0).get("PROMISE_ITMES")));//57承诺事项

                resBean.setOverseasConsignorCode(objToString(result.get(0).get("OverseasConsignorCode")));//58境外发货人代码
                resBean.setOverseasConsignorEname(objToString(result.get(0).get("OverseasConsignorEname")));//59境外发货人名称（外文）
                resBean.setOverseasConsigneeCode(objToString(result.get(0).get("OverseasConsigneeCode")));//60境外收货人编码
                resBean.setOverseasConsigneeEname(objToString(result.get(0).get("OverseasConsigneeEname")));//61境外收货人名称(外文)
                resBean.setTradeCountryStd(objToString(result.get(0).get("TradeCountryStd")));//62起运国/运抵国
                resBean.setTradeAreaCodeStd(objToString(result.get(0).get("TradeAreaCodeStd")));//63贸易国别
                resBean.setDistinatePortStd(objToString(result.get(0).get("DistinatePortStd")));//64经停港/指运港
                resBean.setTrafModeStd(objToString(result.get(0).get("TrafModeStd")));//65运输方式
                resBean.setTradeModeStd(objToString(result.get(0).get("TradeModeStd")));//66贸易方式
                resBean.setFeeCurrStd(objToString(result.get(0).get("FeeCurrStd")));//67运费币制
                resBean.setInsurCurrStd(objToString(result.get(0).get("InsurCurrStd")));//68保险费币制
                resBean.setOtherCurrStd(objToString(result.get(0).get("OtherCurrStd")));//69杂费币制
                resBean.setWrapTypeStd(objToString(result.get(0).get("WrapTypeStd")));//70包装种类
                resBean.setMarkNo(objToString(result.get(0).get("MarkNo")));//71标记唛码
                resBean.setDespPortCode(objToString(result.get(0).get("DespPortCode")));//72启运口岸代码
                resBean.setEntyPortCode(objToString(result.get(0).get("EntyPortCode")));//73入境口岸代码
                resBean.setGoodsPlace(objToString(result.get(0).get("GoodsPlace")));//74存放地点
                resBean.setBlLineNo(objToString(result.get(0).get("BLNo")));//75 B/L号
                resBean.setInspOrgCode(objToString(result.get(0).get("InspOrgCode")));//76口岸检验检疫机关
                resBean.setSpecDeclFlag(objToString(result.get(0).get("SpecDeclFlag")));//77特种业务标识
                resBean.setPurpOrgCode(objToString(result.get(0).get("PurpOrgCode")));//78目的地检验检疫机关
                resBean.setDespDate(objToString(result.get(0).get("DespDate")));//79启运日期
                resBean.setCmplDschrgDt(objToString(result.get(0).get("CmplDschrgDt")));//80卸毕日期
                resBean.setCorrelationReasonFlag(objToString(result.get(0).get("CorrelationReasonFlag")));//81关联理由
                resBean.setVsaOrgCode(objToString(result.get(0).get("VsaOrgCode")));//82领证机关
                resBean.setOrigBoxFlag(objToString(result.get(0).get("OrigBoxFlag")));//83原集装箱标识
                resBean.setDeclareName(objToString(result.get(0).get("DeclareName")));//84报关员姓名
                resBean.setNoOtherPack(objToString(result.get(0).get("NoOtherPack")));//85无其他包装
                resBean.setOrgCode(objToString(result.get(0).get("OrgCode")));//86检验检疫受理机关
                resBean.setOverseasConsignorCname(objToString(result.get(0).get("OverseasConsignorCname")));//87境外发货人名称
                resBean.setOverseasConsignorAddr(objToString(result.get(0).get("OverseasConsignorAddr")));//88境外发货人地址
                resBean.setDomesticConsigneeEname(objToString(result.get(0).get("DomesticConsigneeEname")));//89境内收货人名称（外文）
                resBean.setCorrelationNo(objToString(result.get(0).get("CorrelationNo")));//90关联号码
                resBean.setTradeCiqCode(objToString(result.get(0).get("TradeCiqCode")));//91境内收发货人检验检疫编码
                resBean.setOwnerCiqCode(objToString(result.get(0).get("OwnerCiqCode")));//92消费使用/生产销售单位检验检疫编码
                resBean.setDeclCiqCode(objToString(result.get(0).get("DeclCiqCode")));//93申报单位检验检疫编码
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resBean;
    }
}
