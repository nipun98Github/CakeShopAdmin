package com.admin.esweetadminappfinal.Model;

public class Customer {
    String name;
    String email;
    String mobile;
    String address;
    String googleUId;
    int userStatus;
    String date;
    String ImageUrlPath;
    String fcmId;

    public Customer() {
    }


    public Customer(String name, String email, String mobile, String address, String googleUId, int userStatus, String date, String imageUrlPath, String fcmId) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.googleUId = googleUId;
        this.userStatus = userStatus;
        this.date = date;
        ImageUrlPath = imageUrlPath;
        this.fcmId = fcmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoogleUId() {
        return googleUId;
    }

    public void setGoogleUId(String googleUId) {
        this.googleUId = googleUId;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrlPath() {
        return ImageUrlPath;
    }

    public void setImageUrlPath(String imageUrlPath) {
        ImageUrlPath = imageUrlPath;
    }

    public String getFcmId() {
        return fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }
}
