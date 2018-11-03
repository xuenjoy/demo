package com.tilchina.edi.ui.bean;


import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * @author zoujunwen
 * @create 2018-10-17  10:41
 *
 * 报关表头实体类
 */
public class DecFormHead {

    private String copNo;

    @ExcelField(title = "表头编号", align = ExcelField.Align.CENTER, sort = 1)
    private Integer headId;  //表头编号
    @ExcelField(title = "预录入编号", align = ExcelField.Align.CENTER, sort = 2)
    private String preEntryNo;  //预录入编号
    @ExcelField(title = "收发货人编号", align = ExcelField.Align.CENTER, sort = 3)
    private String proprietorCompanyCode;   //收发货人编号
    @ExcelField(title = "收发货人名称", align = ExcelField.Align.CENTER, sort = 4)
    private String proprietorCompanyName;   //收发货人名称
    @ExcelField(title = "货主单位地区代码", align = ExcelField.Align.CENTER, sort = 5)
    private String districtCode;    //货主单位地区代码
    @ExcelField(title = "货主单位代码", align = ExcelField.Align.CENTER, sort = 6)
    private String ownerCode;   //货主单位代码
    @ExcelField(title = "货主单位名称", align = ExcelField.Align.CENTER, sort = 7)
    private String ownerName; //货主单位名称
    @ExcelField(title = "申报单位代码", align = ExcelField.Align.CENTER, sort = 8)
    private String agentCode; //申报单位代码
    @ExcelField(title = "申报单位名称", align = ExcelField.Align.CENTER, sort = 9)
    private String agentName; //申报单位名称
    @ExcelField(title = "运输方式", align = ExcelField.Align.CENTER, sort = 10)
    private String transportModeCode; //运输方式
    @ExcelField(title = "进出口岸", align = ExcelField.Align.CENTER, sort = 11)
    private String iePort; //进出口岸
    @ExcelField(title = "指运港", align = ExcelField.Align.CENTER, sort = 12)
    private String distinatePortCode; //指运港
    @ExcelField(title = "运输工具代码及名称", align = ExcelField.Align.CENTER, sort = 13)
    private String trafName; //运输工具代码及名称
    @ExcelField(title = "合同号", align = ExcelField.Align.CENTER, sort = 14)
    private String contrNo; //合同号
    @ExcelField(title = "内销比率", align = ExcelField.Align.CENTER, sort = 15)
    private String inRatio; //内销比率
    @ExcelField(title = "提单或运单号", align = ExcelField.Align.CENTER, sort = 16)
    private String billNo; //提单或运单号
    @ExcelField(title = "贸易国（地区）", align = ExcelField.Align.CENTER, sort = 17)
    private String countryOfOdCode; //贸易国（地区）
    @ExcelField(title = "监管方式", align = ExcelField.Align.CENTER, sort = 18)
    private String tradeMethodCode; //监管方式
    @ExcelField(title = "征免性质", align = ExcelField.Align.CENTER, sort = 19)
    private String ncCode; ////征免性质
    @ExcelField(title = "纳税方式", align = ExcelField.Align.CENTER, sort = 20)
    private String payMode; //纳税方式
    @ExcelField(title = "成交方式", align = ExcelField.Align.CENTER, sort = 21)
    private String termsOfDeliveryCode; //成交方式
    @ExcelField(title = "收结汇方式", align = ExcelField.Align.CENTER, sort = 22)
    private String payWay; //收结汇方式
    @ExcelField(title = "运费标记", align = ExcelField.Align.CENTER, sort = 23)
    private String feeMark; //运费标记
    @ExcelField(title = "运费币制", align = ExcelField.Align.CENTER, sort = 24)
    private String feeCurrCode; //运费币制
    @ExcelField(title = "运费/率", align = ExcelField.Align.CENTER, sort = 25)
    private Double feeRate; //运费/率
    @ExcelField(title = "杂费标记", align = ExcelField.Align.CENTER, sort = 26)
    private String otherMark; //杂费标记
    @ExcelField(title = "杂费币制", align = ExcelField.Align.CENTER, sort = 27)
    private String otherCurrCode; //杂费币制
    @ExcelField(title = "杂费/率", align = ExcelField.Align.CENTER, sort = 28)
    private Double otherRate; //杂费/率
    @ExcelField(title = "保险费标记", align = ExcelField.Align.CENTER, sort = 29)
    private String insurMark; //保险费标记
    @ExcelField(title = "保险费币制", align = ExcelField.Align.CENTER, sort = 30)
    private String insurCurrCode; //保险费币制
    @ExcelField(title = "保险费/率", align = ExcelField.Align.CENTER, sort = 31)
    private Double insurRate; //保险费/率
    @ExcelField(title = "件数", align = ExcelField.Align.CENTER, sort = 32)
    private Integer totalPiece; //件数
    @ExcelField(title = "毛重", align = ExcelField.Align.CENTER, sort = 33)
    private Double totalGrossWeight; //毛重
    @ExcelField(title = "净重", align = ExcelField.Align.CENTER, sort = 34)
    private Double totalNetWeight; //净重
    @ExcelField(title = "许可证编号", align = ExcelField.Align.CENTER, sort = 35)
    private String licenceNo; //许可证编号
    @ExcelField(title = "批准文号", align = ExcelField.Align.CENTER, sort = 36)
    private String apprNo; //批准文号
    @ExcelField(title = "备案号", align = ExcelField.Align.CENTER, sort = 37)
    private String poRecordsNo; //备案号
    @ExcelField(title = "进出日期", align = ExcelField.Align.CENTER, sort = 38)
    private String dateOfIe; //进出日期
    @ExcelField(title = "包装种类", align = ExcelField.Align.CENTER, sort = 39)
    private String wrapType; //包装种类
    @ExcelField(title = "申报日期", align = ExcelField.Align.CENTER, sort = 40)
    private String dateOfDeclaration; //申报日期
    @ExcelField(title = "外汇来源", align = ExcelField.Align.CENTER, sort = 41)
    private String exSource; //外汇来源
    @ExcelField(title = "航次号", align = ExcelField.Align.CENTER, sort = 42)
    private String voyageNo; //航次号
    @ExcelField(title = "进出口标志", align = ExcelField.Align.CENTER, sort = 43)
    private String ieFlag; //进出口标志
    @ExcelField(title = "货场代码", align = ExcelField.Align.CENTER, sort = 44)
    private String prdtid; //货场代码
    @ExcelField(title = "保税/监管场所", align = ExcelField.Align.CENTER, sort = 45)
    private String storeNo; //保税/监管场所
    @ExcelField(title = "关联备案号", align = ExcelField.Align.CENTER, sort = 46)
    private String ramanualNo; //关联备案号
    @ExcelField(title = "关联报关单号", align = ExcelField.Align.CENTER, sort = 47)
    private String radeclNo; //关联报关单号
    @ExcelField(title = "报文状态", align = ExcelField.Align.CENTER, sort = 48)
    private String status; //报文状态
    @ExcelField(title = "备注", align = ExcelField.Align.CENTER, sort = 49)
    private String labelRemark; //备注
    @ExcelField(title = "经营单位统一编码", align = ExcelField.Align.CENTER, sort = 50)
    private String tradeCoScc; //经营单位统一编码
    @ExcelField(title = "申报代码统一编码", align = ExcelField.Align.CENTER, sort = 51)
    private String agentCodeScc; //申报代码统一编码
    @ExcelField(title = "货主单位统一编码", align = ExcelField.Align.CENTER, sort = 52)
    private String ownerCodeScc; //货主单位统一编码
    @ExcelField(title = "贸易国别", align = ExcelField.Align.CENTER, sort = 53)
    private String tradeCountryCode; //贸易国别
    @ExcelField(title = "申报地海关", align = ExcelField.Align.CENTER, sort = 54)
    private String portOfIeCode; //申报地海关
    @ExcelField(title = "EDI单据编号", align = ExcelField.Align.CENTER, sort = 55)
    private String ediNo; //EDI单据编号
    @ExcelField(title = "特殊关系确认 + 价格影响确认 +支付特许权使用费确认", align = ExcelField.Align.CENTER, sort = 56)
    private String rprFlag; //特殊关系确认 + 价格影响确认 +支付特许权使用费确认
    @ExcelField(title = "境外发货人代码", align = ExcelField.Align.CENTER, sort = 57)
    private String overseasConsignorCode; //境外发货人代码
    @ExcelField(title = "境外发货人名称（外文）", align = ExcelField.Align.CENTER, sort = 58)
    private String overseasConsignorEname; //境外发货人名称（外文）
    @ExcelField(title = "境外收货人编码", align = ExcelField.Align.CENTER, sort = 59)
    private String overseasConsigneeCode; //境外收货人编码
    @ExcelField(title = "境外收货人名称(外文)", align = ExcelField.Align.CENTER, sort = 60)
    private String overseasConsigneeEname; //境外收货人名称(外文)
    @ExcelField(title = "起运国/运抵国", align = ExcelField.Align.CENTER, sort = 61)
    private String tradeCountryStd; 	//起运国/运抵国
    @ExcelField(title = "贸易国别", align = ExcelField.Align.CENTER, sort = 62)
    private String tradeAreaCodeStd; //贸易国别
    @ExcelField(title = "经停港/指运港", align = ExcelField.Align.CENTER, sort = 63)
    private String distinatePortStd; //经停港/指运港
    @ExcelField(title = "运输方式", align = ExcelField.Align.CENTER, sort = 64)
    private String trafModeStd; //运输方式
    @ExcelField(title = "贸易方式", align = ExcelField.Align.CENTER, sort = 65)
    private String tradeModeStd; //贸易方式
    @ExcelField(title = "运费币制", align = ExcelField.Align.CENTER, sort = 66)
    private String feeCurrStd; //运费币制
    @ExcelField(title = "保险费币制", align = ExcelField.Align.CENTER, sort = 67)
    private String insurCurrStd;//保险费币制
    @ExcelField(title = "杂费币制", align = ExcelField.Align.CENTER, sort = 68)
    private String otherCurrStd;//杂费币制
    @ExcelField(title = "包装种类", align = ExcelField.Align.CENTER, sort = 69)
    private String wrapTypeStd;//包装种类
    @ExcelField(title = "标记唛码", align = ExcelField.Align.CENTER, sort = 70)
    private String markNo;//标记唛码
    @ExcelField(title = "启运口岸代码", align = ExcelField.Align.CENTER, sort = 71)
    private String despPortCode;//启运口岸代码
    @ExcelField(title = "入境口岸代码", align = ExcelField.Align.CENTER, sort = 72)
    private String entyPortCode;//入境口岸代码
    @ExcelField(title = "存放地点", align = ExcelField.Align.CENTER, sort = 73)
    private String goodsPlace;//存放地点
    @ExcelField(title = "口岸检验检疫机关", align = ExcelField.Align.CENTER, sort = 74)
    private String inspOrgCode;//口岸检验检疫机关
    @ExcelField(title = "特种业务标识", align = ExcelField.Align.CENTER, sort = 75)
    private String specDeclFlag;//特种业务标识
    @ExcelField(title = "目的地检验检疫机关", align = ExcelField.Align.CENTER, sort = 76)
    private String purpOrgCode;//目的地检验检疫机关
    @ExcelField(title = "启运日期", align = ExcelField.Align.CENTER, sort = 77)
    private String despDate;//启运日期
    @ExcelField(title = "卸毕日期", align = ExcelField.Align.CENTER, sort = 78)
    private String cmplDschrgDt;//卸毕日期
    @ExcelField(title = "关联理由", align = ExcelField.Align.CENTER, sort = 79)
    private String correlationReasonFlag;//关联理由
    @ExcelField(title = "领证机关", align = ExcelField.Align.CENTER, sort = 80)
    private String vsaOrgCode;//领证机关
    @ExcelField(title = "原集装箱标识", align = ExcelField.Align.CENTER, sort = 81)
    private String origBoxFlag;//原集装箱标识
    @ExcelField(title = "报关员姓名", align = ExcelField.Align.CENTER, sort = 82)
    private String declareName;//报关员姓名
    @ExcelField(title = "无其他包装", align = ExcelField.Align.CENTER, sort = 83)
    private String noOtherPack;//无其他包装
    @ExcelField(title = "检验检疫受理机关", align = ExcelField.Align.CENTER, sort = 84)
    private String orgCode;//检验检疫受理机关
    @ExcelField(title = "境外发货人名称", align = ExcelField.Align.CENTER, sort = 85)
    private String overseasConsignorCname;//境外发货人名称
    @ExcelField(title = "境外发货人地址", align = ExcelField.Align.CENTER, sort = 86)
    private String overseasConsignorAddr;//境外发货人地址
    @ExcelField(title = "境内收货人名称（外文）", align = ExcelField.Align.CENTER, sort = 87)
    private String domesticConsigneeEname;//境内收货人名称（外文）
    @ExcelField(title = "关联号码", align = ExcelField.Align.CENTER, sort = 88)
    private String correlationNo;//关联号码
    @ExcelField(title = "境内收发货人检验检疫编码", align = ExcelField.Align.CENTER, sort = 89)
    private String tradeCiqCode;//境内收发货人检验检疫编码
    @ExcelField(title = "消费使用/生产销售单位检验检疫编码", align = ExcelField.Align.CENTER, sort = 90)
    private String ownerCiqCode;//消费使用/生产销售单位检验检疫编码
    @ExcelField(title = "申报单位检验检疫编码", align = ExcelField.Align.CENTER, sort = 91)
    private String declCiqCode;//申报单位检验检疫编码
    @ExcelField(title = "单据类型", align = ExcelField.Align.CENTER, sort = 92)
    private String invoicesType; //单据类型
    @ExcelField(title = "随附单据编号", align = ExcelField.Align.CENTER, sort = 93)
    private String invoicesCode; //随附单据编号
    @ExcelField(title = "所属单位代码", align = ExcelField.Align.CENTER, sort = 94)
    private String edocOwnerCode;   //所属单位代码
    @ExcelField(title = "所属单位名称", align = ExcelField.Align.CENTER, sort = 95)
    private String edocOwnerName;   //所属单位名称
    @ExcelField(title = "备注", align = ExcelField.Align.CENTER, sort = 96)
    private String opNote;  //备注
    @ExcelField(title = "原产地单据类型", align = ExcelField.Align.CENTER, sort = 97)
    private String certType;    //原产地单据类型
    @ExcelField(title = "单据证书号", align = ExcelField.Align.CENTER, sort = 98)
    private String ecoCertNo;   //单据证书号
    @ExcelField(title = "报关单商品项号", align = ExcelField.Align.CENTER, sort = 99)
    private String decGNo;  //报关单商品项号
    @ExcelField(title = "原产地证书单证项号", align = ExcelField.Align.CENTER, sort = 100)
    private String ecoGNo;  //原产地证书单证项号
    @ExcelField(title = "扩展字段", align = ExcelField.Align.CENTER, sort = 101)
    private String extendFiled; //扩展字段
    @ExcelField(title = "申请单证序号", align = ExcelField.Align.CENTER, sort = 102)
    private String gNo; //申请单证序号
    @ExcelField(title = "申请单证代码", align = ExcelField.Align.CENTER, sort = 103)
    private String appCertCode; //申请单证代码
    @ExcelField(title = "申请单证正本数", align = ExcelField.Align.CENTER, sort = 104)
    private Integer appCertQriQty; //申请单证正本数
    @ExcelField(title = "申请单证副本数", align = ExcelField.Align.CENTER, sort = 105)
    private Integer appCertCopyQty; //申请单证副本数
    @ExcelField(title = "单证代码1", align = ExcelField.Align.CENTER, sort = 106)
    private String certCode1; //单证代码1
    @ExcelField(title = "单证编号1", align = ExcelField.Align.CENTER, sort = 107)
    private String certNo1; //单证编号1
    @ExcelField(title = "单证代码2", align = ExcelField.Align.CENTER, sort = 108)
    private String certCode2; //单证代码2
    @ExcelField(title = "单证编号2", align = ExcelField.Align.CENTER, sort = 109)
    private String certNo2; //单证编号2
    @ExcelField(title = "其他包装信息序号", align = ExcelField.Align.CENTER, sort = 110)
    private String packNo;  //其他包装信息序号
    @ExcelField(title = "包装件数", align = ExcelField.Align.CENTER, sort = 111)
    private String packQty; //包装件数
    @ExcelField(title = "包装材料种类", align = ExcelField.Align.CENTER, sort = 112)
    private String packType; //包装材料种类
    @ExcelField(title = "企业资质信息序号", align = ExcelField.Align.CENTER, sort = 113)
    private String copLimitNo; //企业资质信息序号
    @ExcelField(title = "企业资质编号", align = ExcelField.Align.CENTER, sort = 114)
    private String entQualifNo; //企业资质编号
    @ExcelField(title = "企业资质类别代码", align = ExcelField.Align.CENTER, sort = 115)
    private String entQualifTypeCode; //企业资质类别代码
    @ExcelField(title = "使用人信息序号", align = ExcelField.Align.CENTER, sort = 116)
    private String userNo;  //使用人信息序号
    @ExcelField(title = "使用单位联系人", align = ExcelField.Align.CENTER, sort = 117)
    private String useOrgPersonCode; //使用单位联系人
    @ExcelField(title = "使用单位联系电话", align = ExcelField.Align.CENTER, sort = 118)
    private String useOrgPersonTel; //使用单位联系电话
    @ExcelField(title = "企业承诺信息序号", align = ExcelField.Align.CENTER, sort = 119)
    private String promiseNo; //企业承诺信息序号
    @ExcelField(title = "证明/声明材料代码", align = ExcelField.Align.CENTER, sort = 120)
    private String declarationMaterialCode; //证明/声明材料代码
    @ExcelField(title = "B/L号", align = ExcelField.Align.CENTER, sort = 121)
    private String blLineNo;    //B/L号



    public String getCopNo() { return copNo; }

    public void setCopNo(String copNo) { this.copNo = copNo; }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getPreEntryNo() {
        return preEntryNo;
    }

    public void setPreEntryNo(String preEntryNo) {
        this.preEntryNo = preEntryNo;
    }

    public String getProprietorCompanyCode() {
        return proprietorCompanyCode;
    }

    public void setProprietorCompanyCode(String proprietorCompanyCode) {
        this.proprietorCompanyCode = proprietorCompanyCode;
    }

    public String getProprietorCompanyName() {
        return proprietorCompanyName;
    }

    public void setProprietorCompanyName(String proprietorCompanyName) {
        this.proprietorCompanyName = proprietorCompanyName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getTransportModeCode() {
        return transportModeCode;
    }

    public void setTransportModeCode(String transportModeCode) {
        this.transportModeCode = transportModeCode;
    }

    public String getIePort() {
        return iePort;
    }

    public void setIePort(String iePort) {
        this.iePort = iePort;
    }

    public String getDistinatePortCode() {
        return distinatePortCode;
    }

    public void setDistinatePortCode(String distinatePortCode) {
        this.distinatePortCode = distinatePortCode;
    }

    public String getTrafName() {
        return trafName;
    }

    public void setTrafName(String trafName) {
        this.trafName = trafName;
    }

    public String getContrNo() {
        return contrNo;
    }

    public void setContrNo(String contrNo) {
        this.contrNo = contrNo;
    }

    public String getInRatio() {
        return inRatio;
    }

    public void setInRatio(String inRatio) {
        this.inRatio = inRatio;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCountryOfOdCode() {
        return countryOfOdCode;
    }

    public void setCountryOfOdCode(String countryOfOdCode) {
        this.countryOfOdCode = countryOfOdCode;
    }

    public String getTradeMethodCode() {
        return tradeMethodCode;
    }

    public void setTradeMethodCode(String tradeMethodCode) {
        this.tradeMethodCode = tradeMethodCode;
    }

    public String getNcCode() {
        return ncCode;
    }

    public void setNcCode(String ncCode) {
        this.ncCode = ncCode;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getTermsOfDeliveryCode() {
        return termsOfDeliveryCode;
    }

    public void setTermsOfDeliveryCode(String termsOfDeliveryCode) {
        this.termsOfDeliveryCode = termsOfDeliveryCode;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getFeeMark() {
        return feeMark;
    }

    public void setFeeMark(String feeMark) {
        this.feeMark = feeMark;
    }

    public String getFeeCurrCode() {
        return feeCurrCode;
    }

    public void setFeeCurrCode(String feeCurrCode) {
        this.feeCurrCode = feeCurrCode;
    }

    public Double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Double feeRate) {
        this.feeRate = feeRate;
    }

    public String getOtherMark() {
        return otherMark;
    }

    public void setOtherMark(String otherMark) {
        this.otherMark = otherMark;
    }

    public String getOtherCurrCode() {
        return otherCurrCode;
    }

    public void setOtherCurrCode(String otherCurrCode) {
        this.otherCurrCode = otherCurrCode;
    }

    public Double getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(Double otherRate) {
        this.otherRate = otherRate;
    }

    public String getInsurMark() {
        return insurMark;
    }

    public void setInsurMark(String insurMark) {
        this.insurMark = insurMark;
    }

    public String getInsurCurrCode() {
        return insurCurrCode;
    }

    public void setInsurCurrCode(String insurCurrCode) {
        this.insurCurrCode = insurCurrCode;
    }

    public Double getInsurRate() {
        return insurRate;
    }

    public void setInsurRate(Double insurRate) {
        this.insurRate = insurRate;
    }

    public Integer getTotalPiece() {
        return totalPiece;
    }

    public void setTotalPiece(Integer totalPiece) {
        this.totalPiece = totalPiece;
    }

    public Double getTotalGrossWeight() {
        return totalGrossWeight;
    }

    public void setTotalGrossWeight(Double totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
    }

    public Double getTotalNetWeight() {
        return totalNetWeight;
    }

    public void setTotalNetWeight(Double totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getApprNo() {
        return apprNo;
    }

    public void setApprNo(String apprNo) {
        this.apprNo = apprNo;
    }

    public String getPoRecordsNo() {
        return poRecordsNo;
    }

    public void setPoRecordsNo(String poRecordsNo) {
        this.poRecordsNo = poRecordsNo;
    }

    public String getDateOfIe() {
        return dateOfIe;
    }

    public void setDateOfIe(String dateOfIe) {
        this.dateOfIe = dateOfIe;
    }

    public String getWrapType() {
        return wrapType;
    }

    public void setWrapType(String wrapType) {
        this.wrapType = wrapType;
    }

    public String getDateOfDeclaration() {
        return dateOfDeclaration;
    }

    public void setDateOfDeclaration(String dateOfDeclaration) {
        this.dateOfDeclaration = dateOfDeclaration;
    }

    public String getExSource() {
        return exSource;
    }

    public void setExSource(String exSource) {
        this.exSource = exSource;
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public String getPrdtid() {
        return prdtid;
    }

    public void setPrdtid(String prdtid) {
        this.prdtid = prdtid;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getRamanualNo() {
        return ramanualNo;
    }

    public void setRamanualNo(String ramanualNo) {
        this.ramanualNo = ramanualNo;
    }

    public String getRadeclNo() {
        return radeclNo;
    }

    public void setRadeclNo(String radeclNo) {
        this.radeclNo = radeclNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabelRemark() {
        return labelRemark;
    }

    public void setLabelRemark(String labelRemark) {
        this.labelRemark = labelRemark;
    }

    public String getTradeCoScc() {
        return tradeCoScc;
    }

    public void setTradeCoScc(String tradeCoScc) {
        this.tradeCoScc = tradeCoScc;
    }

    public String getAgentCodeScc() {
        return agentCodeScc;
    }

    public void setAgentCodeScc(String agentCodeScc) {
        this.agentCodeScc = agentCodeScc;
    }

    public String getOwnerCodeScc() {
        return ownerCodeScc;
    }

    public void setOwnerCodeScc(String ownerCodeScc) {
        this.ownerCodeScc = ownerCodeScc;
    }

    public String getTradeCountryCode() {
        return tradeCountryCode;
    }

    public void setTradeCountryCode(String tradeCountryCode) {
        this.tradeCountryCode = tradeCountryCode;
    }

    public String getPortOfIeCode() {
        return portOfIeCode;
    }

    public void setPortOfIeCode(String portOfIeCode) {
        this.portOfIeCode = portOfIeCode;
    }

    public String getEdiNo() {
        return ediNo;
    }

    public void setEdiNo(String ediNo) {
        this.ediNo = ediNo;
    }

    public String getRprFlag() {
        return rprFlag;
    }

    public void setRprFlag(String rprFlag) {
        this.rprFlag = rprFlag;
    }

    public String getOverseasConsignorCode() {
        return overseasConsignorCode;
    }

    public void setOverseasConsignorCode(String overseasConsignorCode) {
        this.overseasConsignorCode = overseasConsignorCode;
    }

    public String getOverseasConsignorEname() {
        return overseasConsignorEname;
    }

    public void setOverseasConsignorEname(String overseasConsignorEname) {
        this.overseasConsignorEname = overseasConsignorEname;
    }

    public String getOverseasConsigneeCode() {
        return overseasConsigneeCode;
    }

    public void setOverseasConsigneeCode(String overseasConsigneeCode) {
        this.overseasConsigneeCode = overseasConsigneeCode;
    }

    public String getOverseasConsigneeEname() {
        return overseasConsigneeEname;
    }

    public void setOverseasConsigneeEname(String overseasConsigneeEname) {
        this.overseasConsigneeEname = overseasConsigneeEname;
    }

    public String getTradeCountryStd() {
        return tradeCountryStd;
    }

    public void setTradeCountryStd(String tradeCountryStd) {
        this.tradeCountryStd = tradeCountryStd;
    }

    public String getTradeAreaCodeStd() {
        return tradeAreaCodeStd;
    }

    public void setTradeAreaCodeStd(String tradeAreaCodeStd) {
        this.tradeAreaCodeStd = tradeAreaCodeStd;
    }

    public String getDistinatePortStd() {
        return distinatePortStd;
    }

    public void setDistinatePortStd(String distinatePortStd) {
        this.distinatePortStd = distinatePortStd;
    }

    public String getTrafModeStd() {
        return trafModeStd;
    }

    public void setTrafModeStd(String trafModeStd) {
        this.trafModeStd = trafModeStd;
    }

    public String getTradeModeStd() {
        return tradeModeStd;
    }

    public void setTradeModeStd(String tradeModeStd) {
        this.tradeModeStd = tradeModeStd;
    }

    public String getFeeCurrStd() {
        return feeCurrStd;
    }

    public void setFeeCurrStd(String feeCurrStd) {
        this.feeCurrStd = feeCurrStd;
    }

    public String getInsurCurrStd() {
        return insurCurrStd;
    }

    public void setInsurCurrStd(String insurCurrStd) {
        this.insurCurrStd = insurCurrStd;
    }

    public String getOtherCurrStd() {
        return otherCurrStd;
    }

    public void setOtherCurrStd(String otherCurrStd) {
        this.otherCurrStd = otherCurrStd;
    }

    public String getWrapTypeStd() {
        return wrapTypeStd;
    }

    public void setWrapTypeStd(String wrapTypeStd) {
        this.wrapTypeStd = wrapTypeStd;
    }

    public String getMarkNo() {
        return markNo;
    }

    public void setMarkNo(String markNo) {
        this.markNo = markNo;
    }

    public String getDespPortCode() {
        return despPortCode;
    }

    public void setDespPortCode(String despPortCode) {
        this.despPortCode = despPortCode;
    }

    public String getEntyPortCode() {
        return entyPortCode;
    }

    public void setEntyPortCode(String entyPortCode) {
        this.entyPortCode = entyPortCode;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getInspOrgCode() {
        return inspOrgCode;
    }

    public void setInspOrgCode(String inspOrgCode) {
        this.inspOrgCode = inspOrgCode;
    }

    public String getSpecDeclFlag() {
        return specDeclFlag;
    }

    public void setSpecDeclFlag(String specDeclFlag) {
        this.specDeclFlag = specDeclFlag;
    }

    public String getPurpOrgCode() {
        return purpOrgCode;
    }

    public void setPurpOrgCode(String purpOrgCode) {
        this.purpOrgCode = purpOrgCode;
    }

    public String getDespDate() {
        return despDate;
    }

    public void setDespDate(String despDate) {
        this.despDate = despDate;
    }

    public String getCmplDschrgDt() {
        return cmplDschrgDt;
    }

    public void setCmplDschrgDt(String cmplDschrgDt) {
        this.cmplDschrgDt = cmplDschrgDt;
    }

    public String getCorrelationReasonFlag() {
        return correlationReasonFlag;
    }

    public void setCorrelationReasonFlag(String correlationReasonFlag) {
        this.correlationReasonFlag = correlationReasonFlag;
    }

    public String getVsaOrgCode() {
        return vsaOrgCode;
    }

    public void setVsaOrgCode(String vsaOrgCode) {
        this.vsaOrgCode = vsaOrgCode;
    }

    public String getOrigBoxFlag() {
        return origBoxFlag;
    }

    public void setOrigBoxFlag(String origBoxFlag) {
        this.origBoxFlag = origBoxFlag;
    }

    public String getDeclareName() {
        return declareName;
    }

    public void setDeclareName(String declareName) {
        this.declareName = declareName;
    }

    public String getNoOtherPack() {
        return noOtherPack;
    }

    public void setNoOtherPack(String noOtherPack) {
        this.noOtherPack = noOtherPack;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOverseasConsignorCname() {
        return overseasConsignorCname;
    }

    public void setOverseasConsignorCname(String overseasConsignorCname) {
        this.overseasConsignorCname = overseasConsignorCname;
    }

    public String getOverseasConsignorAddr() {
        return overseasConsignorAddr;
    }

    public void setOverseasConsignorAddr(String overseasConsignorAddr) {
        this.overseasConsignorAddr = overseasConsignorAddr;
    }

    public String getDomesticConsigneeEname() {
        return domesticConsigneeEname;
    }

    public void setDomesticConsigneeEname(String domesticConsigneeEname) {
        this.domesticConsigneeEname = domesticConsigneeEname;
    }

    public String getCorrelationNo() {
        return correlationNo;
    }

    public void setCorrelationNo(String correlationNo) {
        this.correlationNo = correlationNo;
    }

    public String getTradeCiqCode() {
        return tradeCiqCode;
    }

    public void setTradeCiqCode(String tradeCiqCode) {
        this.tradeCiqCode = tradeCiqCode;
    }

    public String getOwnerCiqCode() {
        return ownerCiqCode;
    }

    public void setOwnerCiqCode(String ownerCiqCode) {
        this.ownerCiqCode = ownerCiqCode;
    }

    public String getDeclCiqCode() {
        return declCiqCode;
    }

    public void setDeclCiqCode(String declCiqCode) {
        this.declCiqCode = declCiqCode;
    }

    public String getInvoicesType() {
        return invoicesType;
    }

    public void setInvoicesType(String invoicesType) {
        this.invoicesType = invoicesType;
    }

    public String getInvoicesCode() {
        return invoicesCode;
    }

    public void setInvoicesCode(String invoicesCode) {
        this.invoicesCode = invoicesCode;
    }

    public String getEdocOwnerCode() {
        return edocOwnerCode;
    }

    public void setEdocOwnerCode(String edocOwnerCode) {
        this.edocOwnerCode = edocOwnerCode;
    }

    public String getEdocOwnerName() {
        return edocOwnerName;
    }

    public void setEdocOwnerName(String edocOwnerName) {
        this.edocOwnerName = edocOwnerName;
    }

    public String getOpNote() {
        return opNote;
    }

    public void setOpNote(String opNote) {
        this.opNote = opNote;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getEcoCertNo() {
        return ecoCertNo;
    }

    public void setEcoCertNo(String ecoCertNo) {
        this.ecoCertNo = ecoCertNo;
    }

    public String getDecGNo() {
        return decGNo;
    }

    public void setDecGNo(String decGNo) {
        this.decGNo = decGNo;
    }

    public String getEcoGNo() {
        return ecoGNo;
    }

    public void setEcoGNo(String ecoGNo) {
        this.ecoGNo = ecoGNo;
    }

    public String getExtendFiled() {
        return extendFiled;
    }

    public void setExtendFiled(String extendFiled) {
        this.extendFiled = extendFiled;
    }

    public String getGNo() {
        return gNo;
    }

    public void setGNo(String gNo) {
        this.gNo = gNo;
    }

    public String getAppCertCode() {
        return appCertCode;
    }

    public void setAppCertCode(String appCertCode) {
        this.appCertCode = appCertCode;
    }

    public Integer getAppCertQriQty() {
        return appCertQriQty;
    }

    public void setAppCertQriQty(Integer appCertQriQty) {
        this.appCertQriQty = appCertQriQty;
    }

    public Integer getAppCertCopyQty() {
        return appCertCopyQty;
    }

    public void setAppCertCopyQty(Integer appCertCopyQty) {
        this.appCertCopyQty = appCertCopyQty;
    }

    public String getCertCode1() {
        return certCode1;
    }

    public void setCertCode1(String certCode1) {
        this.certCode1 = certCode1;
    }

    public String getCertNo1() {
        return certNo1;
    }

    public void setCertNo1(String certNo1) {
        this.certNo1 = certNo1;
    }

    public String getCertCode2() {
        return certCode2;
    }

    public void setCertCode2(String certCode2) {
        this.certCode2 = certCode2;
    }

    public String getCertNo2() {
        return certNo2;
    }

    public void setCertNo2(String certNo2) {
        this.certNo2 = certNo2;
    }

    public String getPackNo() {
        return packNo;
    }

    public void setPackNo(String packNo) {
        this.packNo = packNo;
    }

    public String getPackQty() {
        return packQty;
    }

    public void setPackQty(String packQty) {
        this.packQty = packQty;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getCopLimitNo() {
        return copLimitNo;
    }

    public void setCopLimitNo(String copLimitNo) {
        this.copLimitNo = copLimitNo;
    }

    public String getEntQualifNo() {
        return entQualifNo;
    }

    public void setEntQualifNo(String entQualifNo) {
        this.entQualifNo = entQualifNo;
    }

    public String getEntQualifTypeCode() {
        return entQualifTypeCode;
    }

    public void setEntQualifTypeCode(String entQualifTypeCode) {
        this.entQualifTypeCode = entQualifTypeCode;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUseOrgPersonCode() {
        return useOrgPersonCode;
    }

    public void setUseOrgPersonCode(String useOrgPersonCode) {
        this.useOrgPersonCode = useOrgPersonCode;
    }

    public String getUseOrgPersonTel() {
        return useOrgPersonTel;
    }

    public void setUseOrgPersonTel(String useOrgPersonTel) {
        this.useOrgPersonTel = useOrgPersonTel;
    }

    public String getPromiseNo() {
        return promiseNo;
    }

    public void setPromiseNo(String promiseNo) {
        this.promiseNo = promiseNo;
    }

    public String getDeclarationMaterialCode() {
        return declarationMaterialCode;
    }

    public void setDeclarationMaterialCode(String declarationMaterialCode) {
        this.declarationMaterialCode = declarationMaterialCode;
    }

    public String getBlLineNo() {
        return blLineNo;
    }

    public void setBlLineNo(String blLineNo) {
        this.blLineNo = blLineNo;
    }
}
