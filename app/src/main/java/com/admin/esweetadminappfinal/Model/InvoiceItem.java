package com.admin.esweetadminappfinal.Model;

public class InvoiceItem {

    String itemQty;
    String itemTotal;
    String productDocId;
    String invoiceDocId;

    public InvoiceItem() {
    }

    public InvoiceItem(String itemQty, String itemTotal, String productDocId, String invoiceDocId) {
        this.itemQty = itemQty;
        this.itemTotal = itemTotal;
        this.productDocId = productDocId;
        this.invoiceDocId = invoiceDocId;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(String itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getProductDocId() {
        return productDocId;
    }

    public void setProductDocId(String productDocId) {
        this.productDocId = productDocId;
    }

    public String getInvoiceDocId() {
        return invoiceDocId;
    }

    public void setInvoiceDocId(String invoiceDocId) {
        this.invoiceDocId = invoiceDocId;
    }
}