package com.tilchina.edi.ui.bean;

import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * 关联号码
 */
public class CorrelationNumber {
    @ExcelField(title = "EDI单据编号",align = ExcelField.Align.CENTER,sort = 1)
    private String ediNo;
    @ExcelField(title = "报关单号",align = ExcelField.Align.CENTER,sort = 2)
    private String customsNo;
    @ExcelField(title = "检验检疫编号",align = ExcelField.Align.CENTER,sort = 3)
    private String ciqNo;


    public String getEdiNo() {
        return ediNo;
    }

    public void setEdiNo(String ediNo) {
        this.ediNo = ediNo;
    }

    public String getCustomsNo() {
        return customsNo;
    }

    public void setCustomsNo(String customsNo) {
        this.customsNo = customsNo;
    }

    public String getCiqNo() {
        return ciqNo;
    }

    public void setCiqNo(String ciqNo) {
        this.ciqNo = ciqNo;
    }
}
