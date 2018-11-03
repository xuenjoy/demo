package com.tilchina.edi.ui.bean;


import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * @author zoujunwen
 * @create 2018-10-17  14:04
 *
 * 报关表体实体类
 */
public class DecFormList {
    private String preentryid;  //预录入编号
    @ExcelField(title = "表头编号", align = ExcelField.Align.CENTER, sort = 1)
    private Integer headId;  //表头编号
    @ExcelField(title = "表体编号", align = ExcelField.Align.CENTER, sort = 2)
    private Integer gNo;  //表体编号
    @ExcelField(title = "新贸序号", align = ExcelField.Align.CENTER, sort = 3)
    private String contrItem;	//新贸序号
    @ExcelField(title = "商品编号", align = ExcelField.Align.CENTER, sort = 4)
    private String customCode; 	//商品编号
    @ExcelField(title = "附加编号", align = ExcelField.Align.CENTER, sort = 5)
    private String appendCode; 	//附加编号
    @ExcelField(title = "商品名称", align = ExcelField.Align.CENTER, sort = 6)
    private String goodsName; 	//商品名称
    @ExcelField(title = "商品规格型号", align = ExcelField.Align.CENTER, sort = 7)
    private String spec; 	//商品规格型号
    @ExcelField(title = "原产国/目的国", align = ExcelField.Align.CENTER, sort = 8)
    private String countryOfOriginName; //原产国/目的国
    @ExcelField(title = "申报数量", align = ExcelField.Align.CENTER, sort = 9)
    private Double declarationQty; 	//申报数量
    @ExcelField(title = "申报计量单位", align = ExcelField.Align.CENTER, sort = 10)
    private String declValuationUnit; //申报计量单位
    @ExcelField(title = "法定数量", align = ExcelField.Align.CENTER, sort = 11)
    private Double legalQty1; 		//法定数量
    @ExcelField(title = "法定计量单位（第一计量单位）", align = ExcelField.Align.CENTER, sort = 12)
    private String legalValuationUnit1; 	//法定计量单位
    @ExcelField(title = "第二数量", align = ExcelField.Align.CENTER, sort = 13)
    private Double legalQty2; 		//第二数量
    @ExcelField(title = "第二计量单位", align = ExcelField.Align.CENTER, sort = 14)
    private String legalValuationUnit2; //第二计量单位
    @ExcelField(title = "成交币制", align = ExcelField.Align.CENTER, sort = 15)
    private String currencyCode;	//成交币制
    @ExcelField(title = "申报单价", align = ExcelField.Align.CENTER, sort = 16)
    private Double unitPrice; 		//申报单价
    @ExcelField(title = "成交总价", align = ExcelField.Align.CENTER, sort = 17)
    private Double amountPrice; 	//成交总价
    @ExcelField(title = "征免方式", align = ExcelField.Align.CENTER, sort = 18)
    private String ncDetailCode; 		//征免方式
    @ExcelField(title = "用途", align = ExcelField.Align.CENTER, sort = 19)
    private String purposeCode; 		//用途
    @ExcelField(title = "加工成品版本号", align = ExcelField.Align.CENTER, sort = 20)
    private String goodsVersion;  //加工成品版本号
    @ExcelField(title = "加工料件/成品货号", align = ExcelField.Align.CENTER, sort = 21)
    private String goodsId;  //加工料件/成品货号
    @ExcelField(title = "毛重", align = ExcelField.Align.CENTER, sort = 22)
    private String grossWeight;	//毛重
    @ExcelField(title = "净重", align = ExcelField.Align.CENTER, sort = 23)
    private String netWeight;	//净重
    @ExcelField(title = "工缴费", align = ExcelField.Align.CENTER, sort = 24)
    private String workUsd;	//工缴费
    @ExcelField(title = "原产国/目的国", align = ExcelField.Align.CENTER, sort = 25)
    private String countryOfDestCode; 	//原产国/目的国
    @ExcelField(title = "境内目的地/境内货源地", align = ExcelField.Align.CENTER, sort = 26)
    private String districtCode;	//境内目的地/境内货源地
    @ExcelField(title = "原产国/目的国", align = ExcelField.Align.CENTER, sort = 27)
    private String originCountryStd;    //原产国/目的国
    @ExcelField(title = "目的原产国/目的国国", align = ExcelField.Align.CENTER, sort = 28)
    private String destinationCountryStd;	//目的原产国/目的国国
    @ExcelField(title = "成交币制", align = ExcelField.Align.CENTER, sort = 29)
    private String tradeCurrStd;  //成交币制
    @ExcelField(title = "申报计量单位", align = ExcelField.Align.CENTER, sort = 30)
    private String gUnitStd;	//申报计量单位
    @ExcelField(title = "法定计量单位(第一计量单位)", align = ExcelField.Align.CENTER, sort = 31)
    private String firstUnitStd;	//法定计量单位(第一计量单位)
    @ExcelField(title = "第二计量单位", align = ExcelField.Align.CENTER, sort = 32)
    private String secondUnitStd;	//第二计量单位
    @ExcelField(title = "检验检疫编码", align = ExcelField.Align.CENTER, sort = 33)
    private String ciqCode; 	//检验检疫编码
    @ExcelField(title = "商品英文名称", align = ExcelField.Align.CENTER, sort = 34)
    private String declGoodsEname;	//商品英文名称
    @ExcelField(title = "原产地区代码", align = ExcelField.Align.CENTER, sort = 35)
    private String origPlaceCode;	//原产地区代码
    @ExcelField(title = "用途代码", align = ExcelField.Align.CENTER, sort = 36)
    private String purpose;		//用途代码
    @ExcelField(title = "产品有效期", align = ExcelField.Align.CENTER, sort = 37)
    private String prodValidDt;	 //产品有效期
    @ExcelField(title = "产品保质期", align = ExcelField.Align.CENTER, sort = 38)
    private String prodQgp;	   //产品保质期
    @ExcelField(title = "货物属性代码", align = ExcelField.Align.CENTER, sort = 39)
    private String goodsAttr;	//货物属性代码
    @ExcelField(title = "成份/原料/组份", align = ExcelField.Align.CENTER, sort = 40)
    private String stuff;		//成份/原料/组份
    @ExcelField(title = "UN编码", align = ExcelField.Align.CENTER, sort = 41)
    private String unCode;	//UN编码
    @ExcelField(title = "危险货物名称", align = ExcelField.Align.CENTER, sort = 42)
    private String dangName; //危险货物名称
    @ExcelField(title = "危包类别", align = ExcelField.Align.CENTER, sort = 43)
    private String dangPackType;	//危包类别
    @ExcelField(title = "危包规格", align = ExcelField.Align.CENTER, sort = 44)
    private String dangPackSpec;	//危包规格
    @ExcelField(title = "境外生产企业名称", align = ExcelField.Align.CENTER, sort = 45)
    private String engManEntCnm;	//境外生产企业名称
    @ExcelField(title = "非危险化学品", align = ExcelField.Align.CENTER, sort = 46)
    private String noDangFlag;	//非危险化学品
    @ExcelField(title = "目的地代码", align = ExcelField.Align.CENTER, sort = 47)
    private String destCode;	//目的地代码
    @ExcelField(title = "检验检疫货物规格", align = ExcelField.Align.CENTER, sort = 48)
    private String goodsSpec;	//检验检疫货物规格
    @ExcelField(title = "货物型号", align = ExcelField.Align.CENTER, sort = 49)
    private String goodsModel;	//货物型号
    @ExcelField(title = "货物品牌", align = ExcelField.Align.CENTER, sort = 50)
    private String goodsBrand;	//货物品牌
    @ExcelField(title = "生产日期", align = ExcelField.Align.CENTER, sort = 51)
    private String produceDate;	 //生产日期
    @ExcelField(title = "生产批号", align = ExcelField.Align.CENTER, sort = 52)
    private String prodBatchNo;	//生产批号
    @ExcelField(title = "检验检疫名称", align = ExcelField.Align.CENTER, sort = 53)
    private String ciqName;		//检验检疫名称
    @ExcelField(title = "生产单位注册号", align = ExcelField.Align.CENTER, sort = 54)
    private String mnufctrRegNo;	//生产单位注册号
    @ExcelField(title = "生产单位名称", align = ExcelField.Align.CENTER, sort = 55)
    private String mnufctrRegName;	//生产单位名称


    public String getPreentryid() { return preentryid; }

    public void setPreentryid(String preentryid) { this.preentryid = preentryid; }

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

    public String getContrItem() {
        return contrItem;
    }

    public void setContrItem(String contrItem) {
        this.contrItem = contrItem;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public String getAppendCode() {
        return appendCode;
    }

    public void setAppendCode(String appendCode) {
        this.appendCode = appendCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) { this.goodsName = goodsName;  }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getCountryOfOriginName() {
        return countryOfOriginName;
    }

    public void setCountryOfOriginName(String countryOfOriginName) {
        this.countryOfOriginName = countryOfOriginName;
    }

    public Double getDeclarationQty() {
        return declarationQty;
    }

    public void setDeclarationQty(Double declarationQty) {
        this.declarationQty = declarationQty;
    }

    public String getDeclValuationUnit() {
        return declValuationUnit;
    }

    public void setDeclValuationUnit(String declValuationUnit) {
        this.declValuationUnit = declValuationUnit;
    }

    public Double getLegalQty1() {
        return legalQty1;
    }

    public void setLegalQty1(Double legalQty1) {
        this.legalQty1 = legalQty1;
    }

    public String getLegalValuationUnit1() {
        return legalValuationUnit1;
    }

    public void setLegalValuationUnit1(String legalValuationUnit1) {
        this.legalValuationUnit1 = legalValuationUnit1;
    }

    public Double getLegalQty2() {
        return legalQty2;
    }

    public void setLegalQty2(Double legalQty2) {
        this.legalQty2 = legalQty2;
    }

    public String getLegalValuationUnit2() {
        return legalValuationUnit2;
    }

    public void setLegalValuationUnit2(String legalValuationUnit2) {
        this.legalValuationUnit2 = legalValuationUnit2;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(Double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getNcDetailCode() {
        return ncDetailCode;
    }

    public void setNcDetailCode(String ncDetailCode) {
        this.ncDetailCode = ncDetailCode;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public String getGoodsVersion() {
        return goodsVersion;
    }

    public void setGoodsVersion(String goodsVersion) {
        this.goodsVersion = goodsVersion;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getWorkUsd() {
        return workUsd;
    }

    public void setWorkUsd(String workUsd) {
        this.workUsd = workUsd;
    }

    public String getCountryOfDestCode() {
        return countryOfDestCode;
    }

    public void setCountryOfDestCode(String countryOfDestCode) {
        this.countryOfDestCode = countryOfDestCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOriginCountryStd() {
        return originCountryStd;
    }

    public void setOriginCountryStd(String originCountryStd) {
        this.originCountryStd = originCountryStd;
    }

    public String getDestinationCountryStd() {
        return destinationCountryStd;
    }

    public void setDestinationCountryStd(String destinationCountryStd) {
        this.destinationCountryStd = destinationCountryStd;
    }

    public String getTradeCurrStd() {
        return tradeCurrStd;
    }

    public void setTradeCurrStd(String tradeCurrStd) {
        this.tradeCurrStd = tradeCurrStd;
    }

    public String getGUnitStd() {
        return gUnitStd;
    }

    public void setGUnitStd(String gUnitStd) {
        this.gUnitStd = gUnitStd;
    }

    public String getFirstUnitStd() {
        return firstUnitStd;
    }

    public void setFirstUnitStd(String firstUnitStd) {
        this.firstUnitStd = firstUnitStd;
    }

    public String getSecondUnitStd() {
        return secondUnitStd;
    }

    public void setSecondUnitStd(String secondUnitStd) {
        this.secondUnitStd = secondUnitStd;
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode;
    }

    public String getDeclGoodsEname() {
        return declGoodsEname;
    }

    public void setDeclGoodsEname(String declGoodsEname) {
        this.declGoodsEname = declGoodsEname;
    }

    public String getOrigPlaceCode() {
        return origPlaceCode;
    }

    public void setOrigPlaceCode(String origPlaceCode) {
        this.origPlaceCode = origPlaceCode;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getProdValidDt() {
        return prodValidDt;
    }

    public void setProdValidDt(String prodValidDt) {
        this.prodValidDt = prodValidDt;
    }

    public String getProdQgp() {
        return prodQgp;
    }

    public void setProdQgp(String prodQgp) {
        this.prodQgp = prodQgp;
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public String getStuff() {
        return stuff;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }

    public String getUnCode() {
        return unCode;
    }

    public void setUnCode(String unCode) {
        this.unCode = unCode;
    }

    public String getDangName() {
        return dangName;
    }

    public void setDangName(String dangName) {
        this.dangName = dangName;
    }

    public String getDangPackType() {
        return dangPackType;
    }

    public void setDangPackType(String dangPackType) {
        this.dangPackType = dangPackType;
    }

    public String getDangPackSpec() {
        return dangPackSpec;
    }

    public void setDangPackSpec(String dangPackSpec) {
        this.dangPackSpec = dangPackSpec;
    }

    public String getEngManEntCnm() {
        return engManEntCnm;
    }

    public void setEngManEntCnm(String engManEntCnm) {
        this.engManEntCnm = engManEntCnm;
    }

    public String getNoDangFlag() {
        return noDangFlag;
    }

    public void setNoDangFlag(String noDangFlag) {
        this.noDangFlag = noDangFlag;
    }

    public String getDestCode() {
        return destCode;
    }

    public void setDestCode(String destCode) {
        this.destCode = destCode;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getProdBatchNo() {
        return prodBatchNo;
    }

    public void setProdBatchNo(String prodBatchNo) {
        this.prodBatchNo = prodBatchNo;
    }

    public String getCiqName() {
        return ciqName;
    }

    public void setCiqName(String ciqName) {
        this.ciqName = ciqName;
    }

    public String getMnufctrRegNo() {
        return mnufctrRegNo;
    }

    public void setMnufctrRegNo(String mnufctrRegNo) {
        this.mnufctrRegNo = mnufctrRegNo;
    }

    public String getMnufctrRegName() {
        return mnufctrRegName;
    }

    public void setMnufctrRegName(String mnufctrRegName) {
        this.mnufctrRegName = mnufctrRegName;
    }
}
