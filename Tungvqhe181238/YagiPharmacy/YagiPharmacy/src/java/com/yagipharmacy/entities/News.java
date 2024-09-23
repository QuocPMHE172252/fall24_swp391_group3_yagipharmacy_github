/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author admin
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class News {
    private Long newsId;
    private Long newsCategoryId;
    private Long creatorId;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String newsHashtag;
    private Long updatedId;
    private String createdDate;
    private boolean isDeleted;
    private  NewsCategory category;
}