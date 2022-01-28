package com.admin.esweetadminappfinal.Model;

public class Invoice {


    String paymentType;
    String date;
    String time;
    double subTotal;
    String recipientName;
    String customerDocId;
    int contactNumber;
    String address;
    int postalCode;
    String status;

    public Invoice() {
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getCustomerDocId() {
        return customerDocId;
    }

    public void setCustomerDocId(String customerDocId) {
        this.customerDocId = customerDocId;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Invoice(String paymentType, String date, String time, double subTotal, String recipientName, String customerDocId, int contactNumber, String address, int postalCode, String status) {
        this.paymentType = paymentType;
        this.date = date;
        this.time = time;
        this.subTotal = subTotal;
        this.recipientName = recipientName;
        this.customerDocId = customerDocId;
        this.contactNumber = contactNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.status = status;
    }
}
