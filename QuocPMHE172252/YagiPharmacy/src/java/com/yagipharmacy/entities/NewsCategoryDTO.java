/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

import java.util.ArrayList;

/**
 *
 * @author author
 */
public class NewsCategoryDTO {

    private Long newsCategoryId;
    private Long newsCategoryParentId;
    private Long newsCategoryLevel;
    private String newsCategoryName;
    private String newsCategoryDetail;
    private String newsImg;
    private boolean isDelete;
    private ArrayList<NewsCategory> categories;

    public NewsCategoryDTO(NewsCategory data) {
        this.newsCategoryId = data.getNewsCategoryId();
        this.newsCategoryParentId = data.getNewsCategoryParentId();
        this.newsCategoryLevel = data.getNewsCategoryLevel();
        this.newsCategoryName = data.getNewsCategoryName();
        this.newsCategoryDetail = data.getNewsCategoryDetail();
        this.newsImg = data.getNewsImg();
        this.isDelete = data.isDelete();
    }

    public Long getNewsCategoryId() {
        return newsCategoryId;
    }

    public void setNewsCategoryId(Long newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public Long getNewsCategoryParentId() {
        return newsCategoryParentId;
    }

    public void setNewsCategoryParentId(Long newsCategoryParentId) {
        this.newsCategoryParentId = newsCategoryParentId;
    }

    public Long getNewsCategoryLevel() {
        return newsCategoryLevel;
    }

    public void setNewsCategoryLevel(Long newsCategoryLevel) {
        this.newsCategoryLevel = newsCategoryLevel;
    }

    public String getNewsCategoryName() {
        return newsCategoryName;
    }

    public void setNewsCategoryName(String newsCategoryName) {
        this.newsCategoryName = newsCategoryName;
    }

    public String getNewsCategoryDetail() {
        return newsCategoryDetail;
    }

    public void setNewsCategoryDetail(String newsCategoryDetail) {
        this.newsCategoryDetail = newsCategoryDetail;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ArrayList<NewsCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<NewsCategory> categories) {
        this.categories = categories;
    }

}
