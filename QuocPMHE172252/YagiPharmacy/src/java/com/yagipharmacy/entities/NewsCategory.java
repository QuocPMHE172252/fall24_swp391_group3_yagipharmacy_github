/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

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
public class NewsCategory {

    private Long newsCategoryId;
    private Long newsCategoryParentId;
    private Long newsCategoryLevel;
    private String newsCategoryName;
    private String newsCategoryDetail;
    private String newsImg;
    private boolean isDelete;
    private int numberNews;
    private NewsCategory parentNewsCategory;
}
