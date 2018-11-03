package com.tilchina.edi.ui.bean;

import com.tilchina.edi.util.excel.annotaion.ExcelField;

/**
 * 车辆证明书
 */
public class CarInfo {
    @ExcelField(title = "OperType",align = ExcelField.Align.CENTER,sort = 1,type = ExcelField.Type.EXPORT)
    private String operType;
    @ExcelField(title = "ClientSeq",align = ExcelField.Align.CENTER,sort = 2,type = ExcelField.Type.EXPORT)
    private  String clientSeq;
    @ExcelField(title = "PreNo",align = ExcelField.Align.CENTER,sort = 3,type = ExcelField.Type.EXPORT)
    private  String preNo;
    @ExcelField(title = "EnterId",align = ExcelField.Align.CENTER,sort = 4)
    private String enterId;
    @ExcelField(title = "Gno",align = ExcelField.Align.CENTER,sort = 5)
    private String gNo;
    @ExcelField(title = "CarCoverNo",align = ExcelField.Align.CENTER,sort = 7)
    private String carCoverNo;
    @ExcelField(title = "Color",align = ExcelField.Align.CENTER,sort = 8)
    private String color;
    @ExcelField(title = "GSeqNo",align = ExcelField.Align.CENTER,sort = 6,type = ExcelField.Type.EXPORT)
    private  String gSeqNo;
    @ExcelField(title = "EnergyType",align = ExcelField.Align.CENTER,sort = 9)
    private String energyType;
    @ExcelField(title = "EngineNo",align = ExcelField.Align.CENTER,sort = 10)
    private String engineNo;
    @ExcelField(title = "QtyExhaust",align = ExcelField.Align.CENTER,sort = 11)
    private String qtyExhaust;
    @ExcelField(title = "ElecMotorNo",align = ExcelField.Align.CENTER,sort = 12)
    private String elecMotorNo;
    @ExcelField(title = "ElecMotorPower",align = ExcelField.Align.CENTER,sort = 13)
    private String elecMotorPower;
    @ExcelField(title = "ManufactureDate",align = ExcelField.Align.CENTER,sort = 14)
    private String manufactureDate;
    @ExcelField(title = "CarType",align = ExcelField.Align.CENTER,sort = 15,type = ExcelField.Type.EXPORT)
    private  String carType;
    @ExcelField(title = "OwnerTel",align = ExcelField.Align.CENTER,sort = 16,type = ExcelField.Type.EXPORT)
    private  String ownerTel;
    @ExcelField(title = "OtherContactMode",align = ExcelField.Align.CENTER,sort = 17,type = ExcelField.Type.EXPORT)
    private  String otherContactMode;
    @ExcelField(title = "GName",align = ExcelField.Align.CENTER,sort = 18,type = ExcelField.Type.EXPORT)
    private  String gName;
    @ExcelField(title = "GModel",align = ExcelField.Align.CENTER,sort = 19,type = ExcelField.Type.EXPORT)
    private  String gModel;
    @ExcelField(title = "Status",align = ExcelField.Align.CENTER,sort = 20,type = ExcelField.Type.EXPORT)
    private  String status;
    @ExcelField(title = "DateResu",align = ExcelField.Align.CENTER,sort = 21,type = ExcelField.Type.EXPORT)
    private  String dateResu;

    public String getEnterId() {  return enterId;  }

    public void setEnterId(String enterId) {
        this.enterId = enterId;
    }

    public String getGNo() {  return gNo;  }

    public void setGNo(String gNo) {
        this.gNo = gNo;
    }

    public String getCarCoverNo() {
        return carCoverNo;
    }

    public void setCarCoverNo(String carCoverNo) {
        this.carCoverNo = carCoverNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getQtyExhaust() {
        return qtyExhaust;
    }

    public void setQtyExhaust(String qtyExhaust) {
        this.qtyExhaust = qtyExhaust;
    }

    public String getElecMotorNo() {
        return elecMotorNo;
    }
    public void setElecMotorNo(String elecMotorNo) {
        this.elecMotorNo = elecMotorNo;
    }

    public String getElecMotorPower() {
        return elecMotorPower;
    }

    public void setElecMotorPower(String elecMotorPower) {
        this.elecMotorPower = elecMotorPower;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getClientSeq() {
        return clientSeq;
    }

    public void setClientSeq(String clientSeq) {
        this.clientSeq = clientSeq;
    }

    public String getPreNo() {
        return preNo;
    }

    public void setPreNo(String preNo) {
        this.preNo = preNo;
    }

    public String getGSeqNo() {
        return gSeqNo;
    }

    public void setGSeqNo(String gSeqNo) {
        this.gSeqNo = gSeqNo;
    }


    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
    }

    public String getOtherContactMode() {
        return otherContactMode;
    }

    public void setOtherContactMode(String otherContactMode) {
        this.otherContactMode = otherContactMode;
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }

    public String getGModel() {
        return gModel;
    }

    public void setGModel(String gModel) {
        this.gModel = gModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateResu() {
        return dateResu;
    }

    public void setDateResu(String dateResu) {
        this.dateResu = dateResu;
    }
}
