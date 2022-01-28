package com.admin.esweetadminappfinal.Model;

public class Admin {
    String name;
    String email;
    String mobile;
    String password;
    String googleUId;
    String adminImageURL;
    double shopLatitude;
    double shopLongitude;



    public Admin() {
    }

    public Admin(String name, String email, String mobile, String password, String googleUId, String adminImageURL, double shopLatitude, double shopLongitude) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.googleUId = googleUId;
        this.adminImageURL = adminImageURL;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGoogleUId() {
        return googleUId;
    }

    public void setGoogleUId(String googleUId) {
        this.googleUId = googleUId;
    }

    public String getAdminImageURL() {
        return adminImageURL;
    }

    public void setAdminImageURL(String adminImageURL) {
        this.adminImageURL = adminImageURL;
    }

    public double getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(double shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public double getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(double shopLongitude) {
        this.shopLongitude = shopLongitude;
    }
}
