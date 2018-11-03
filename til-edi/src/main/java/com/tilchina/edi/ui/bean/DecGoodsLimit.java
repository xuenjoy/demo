package com.tilchina.edi.ui.bean;


import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * @author zoujunwen
 * @create 2018-10-17  15:47
 *
 * 许可证信息实体类
 */
public class DecGoodsLimit {
    @ExcelField(title = "表头编号", align = ExcelField.Align.CENTER, sort = 1)
    private Integer headId;  //表头编号
    @ExcelField(title = "表体编号", align = ExcelField.Align.CENTER, sort = 2)
    private Integer gNo;  //表体编号
    @ExcelField(title = "许可证信息序号", align = ExcelField.Align.CENTER, sort = 3)
    private String licenseInfoNo;  //许可证信息序号
    @ExcelField(title = "许可证类别代码", align = ExcelField.Align.CENTER, sort = 4)
    private String licenseTypeCode;  //许可证类别代码
    @ExcelField(title = "许可证编号", align = ExcelField.Align.CENTER, sort = 5)
    private String licenseNo;  //许可证编号
    @ExcelField(title = "许可证核销明细序号", align = ExcelField.Align.CENTER, sort = 6)
    private String licenseDetailNo;  //许可证核销明细序号
    @ExcelField(title = "许可证核销数量", align = ExcelField.Align.CENTER, sort = 7)
    private String licenseQty;  //许可证核销数量

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

    public String getLicenseTypeCode() {
        return licenseTypeCode;
    }

    public void setLicenseTypeCode(String licenseTypeCode) {
        this.licenseTypeCode = licenseTypeCode;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseDetailNo() {
        return licenseDetailNo;
    }

    public void setLicenseDetailNo(String licenseDetailNo) {
        this.licenseDetailNo = licenseDetailNo;
    }

    public String getLicenseQty() {
        return licenseQty;
    }

    public void setLicenseQty(String licenseQty) {
        this.licenseQty = licenseQty;
    }
}
