package com.ashinetech.bharatration.model;

/**
 * Created by Vignesh on 25-Jul-2015.
 */

public class General {

    private String notifyUrl;
    private String continueUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private String totalAmount;
    private String extOrderId;

    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    public String getContinueUrl() {
        return continueUrl;
    }
    public void setContinueUrl(String continueUrl) {
        this.continueUrl = continueUrl;
    }
    public String getCustomerIp() {
        return customerIp;
    }
    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }
    public String getMerchantPosId() {
        return merchantPosId;
    }
    public void setMerchantPosId(String merchantPosId) {
        this.merchantPosId = merchantPosId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getExtOrderId() {
        return extOrderId;
    }
    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }

}

