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
public class ProductCategory {

    private Long productCategoryId;
    private Long productCategoryParentId;
    private Long productCategoryLevel;
    private String productCategoryCode;
    private String productCategoryName;
    private String productCategoryDetail;
    private boolean isDeleted;
    private ProductCategory parentProductCategory;

}
