package com.admin.esweetadminappfinal.Model;

public class Category {
    public Category() {
    }

    public Category(String categoryName, String categoryImageUrl) {
        CategoryName = categoryName;
        CategoryImageUrl = categoryImageUrl;
    }

    String CategoryName;
    String CategoryImageUrl;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return CategoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        CategoryImageUrl = categoryImageUrl;
    }
}
