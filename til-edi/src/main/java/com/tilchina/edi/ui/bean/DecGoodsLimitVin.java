package com.tilchina.edi.ui.bean;

import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * @author zoujunwen
 * @create 2018-10-17  15:47
 *
 * 许可证信VIN息实体类
 */
public class DecGoodsLimitVin {
    @ExcelField(title = "表头编号", align = ExcelField.Align.CENTER, sort = 1)
    private Integer headId;  //表头编号
    @ExcelField(title = "表体编号", align = ExcelField.Align.CENTER, sort = 2)
    private Integer gNo;  //表体编号
    @ExcelField(title = "许可证信息序号", align = ExcelField.Align.CENTER, sort = 3)
    private String licenseInfoNo;  //许可证信息序号
    @ExcelField(title = "许可证编号", align = ExcelField.Align.CENTER, sort = 4)
    private String licenseNo;  //许可证编号
    @ExcelField(title = "许可证类别代码", align = ExcelField.Align.CENTER, sort = 5)
    private String licenseTypeCode;  //许可证类别代码
    @ExcelField(title = "VIN序号", align = ExcelField.Align.CENTER, sort = 6)
    private String vinNo;  //VIN序号
    @ExcelField(title = "提/运单日期", align = ExcelField.Align.CENTER, sort = 7)
    private String waybillDate;  //提/运单日期
    @ExcelField(title = "质量保质期", align = ExcelField.Align.CENTER, sort = 8)
    private String qualityQgp;  //质量保质期
    @ExcelField(title = "发动机号或电机号", align = ExcelField.Align.CENTER, sort = 9)
    private String motorNo;  //发动机号或电机号
    @ExcelField(title = "车辆识别代码", align = ExcelField.Align.CENTER, sort = 10)
    private String vinCode;  //车辆识别代码
    @ExcelField(title = "底盘(车架)号", align = ExcelField.Align.CENTER, sort = 11)
    private String vin;  //底盘(车架)号
    @ExcelField(title = "发票所列数量", align = ExcelField.Align.CENTER, sort = 12)
    private String invoiceQty;  //发票所列数量
    @ExcelField(title = "品名(中文名称)", align = ExcelField.Align.CENTER, sort = 13)
    private String descriptionCn;  //品名(中文名称)
    @ExcelField(title = "品名(英文名称)", align = ExcelField.Align.CENTER, sort = 14)
    private String descriptionEng;  //品名(英文名称)
    @ExcelField(title = "型号(英文)", align = ExcelField.Align.CENTER, sort = 15)
    private String type;  //型号(英文)
    @ExcelField(title = "单价", align = ExcelField.Align.CENTER, sort = 16)
    private Double unitPrice;  //单价
    @ExcelField(title = "发票号", align = ExcelField.Align.CENTER, sort = 17)
    private String invoiceNo;  //发票号

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public Integer getGNo() {
        return gNo;
    }

    public void setGNo(Integer gNo) {
        this.gNo = gNo;
    }

    public String getLicenseInfoNo() {
        return licenseInfoNo;
    }

    public void setLicenseInfoNo(String licenseInfoNo) {
        this.licenseInfoNo = licenseInfoNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseTypeCode() {
        return licenseTypeCode;
    }

    public void setLicenseTypeCode(String licenseTypeCode) {
        this.licenseTypeCode = licenseTypeCode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getWaybillDate() {
        return waybillDate;
    }

    public void setWaybillDate(String waybillDate) {
        this.waybillDate = waybillDate;
    }

    public String getQualityQgp() {
        return qualityQgp;
    }

    public void setQualityQgp(String qualityQgp) {
        this.qualityQgp = qualityQgp;
    }

    public String getMotorNo() {
        return motorNo;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getInvoiceQty() {
        return invoiceQty;
    }

    public void setInvoiceQty(String invoiceQty) {
        this.invoiceQty = invoiceQty;
    }

    public String getDescriptionCn() {
        return descriptionCn;
    }

    public void setDescriptionCn(String descriptionCn) {
        this.descriptionCn = descriptionCn;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
