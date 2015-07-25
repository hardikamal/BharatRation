package com.ashinetech.bharatration.model;

/**
 * Created by Vignesh on 25-Jul-2015.
 */
import java.util.List;


public class Purchase {

    private List<Products> products;
    private Invoice invoice;
    private Delivery delivery;
    private Buyer buyer;
    private String notifyUrl;
    private String continueUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private String totalAmount;
    private String extOrderId;



    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public Buyer getBuyer() {
        return buyer;
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }



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
    public List<Products> getProducts() {
        return products;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }
    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }

}
