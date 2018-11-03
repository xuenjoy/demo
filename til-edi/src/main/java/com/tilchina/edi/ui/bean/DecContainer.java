package com.tilchina.edi.ui.bean;


import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * @author zoujunwen
 * @create 2018-10-17  15:43
 *
 * 报关集装箱实体类
 */
public class DecContainer {
    @ExcelField(title = "HeadId", align = ExcelField.Align.CENTER, sort = 1)
    private String headId;  //表头编号
    @ExcelField(title = "ContainerNo", align = ExcelField.Align.CENTER, sort = 2)
    private String containerNo; 	//集装箱号
    @ExcelField(title = "ContainerModel", align = ExcelField.Align.CENTER, sort = 3)
    private String containerModel; 	//集装箱规格
    @ExcelField(title = "ContainerWT", align = ExcelField.Align.CENTER, sort = 4)
    private String containerWT; 	//集装箱自重
    @ExcelField(title = "ContainerMdStd", align = ExcelField.Align.CENTER, sort = 5)
    private String containerMdStd;	//集装箱规格CONTAINER_MODEL的新版的参数代码
    @ExcelField(title = "GoodsNo", align = ExcelField.Align.CENTER, sort = 6)
    private String goodsNo; 		//商品项号 用半角逗号分隔，如“1,3”
    @ExcelField(title = "LclFlag", align = ExcelField.Align.CENTER, sort = 7)
    private String lclFlag; 		//拼箱标识0-否 1-是
    @ExcelField(title = "GoodsContaWt", align = ExcelField.Align.CENTER, sort = 8)
    private String goodsContaWt; 	//箱货重量

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getContainerModel() {
        return containerModel;
    }

    public void setContainerModel(String containerModel) {
        this.containerModel = containerModel;
    }

    public String getContainerWT() {
        return containerWT;
    }

    public void setContainerWT(String containerWT) {
        this.containerWT = containerWT;
    }

    public String getContainerMdStd() {
        return containerMdStd;
    }

    public void setContainerMdStd(String containerMdStd) {
        this.containerMdStd = containerMdStd;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getLclFlag() {
        return lclFlag;
    }

    public void setLclFlag(String lclFlag) {
        this.lclFlag = lclFlag;
    }

    public String getGoodsContaWt() {
        return goodsContaWt;
    }

    public void setGoodsContaWt(String goodsContaWt) {
        this.goodsContaWt = goodsContaWt;
    }
}
