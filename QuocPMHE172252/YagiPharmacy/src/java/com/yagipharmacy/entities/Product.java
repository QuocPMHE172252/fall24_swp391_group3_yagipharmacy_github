/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author admin
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String productCode;
    private Long authorId;
    private Long productCategoryId;
    private String productCountryCode;
    private String brand;
    private String productTarget;
    private String productName;
    private String dosageForm;
    private String productSpecification;
    private String productDescription;
    private Date createdDate;
    private boolean isPrescription;
    private boolean isDeleted;
    private ProductCategory productCategory;
    private Supplier supplier;
    private User author;
    private List<ProductExcipient> productExcipients;
    private  List<ProductUnit> productUnits;
    private List<ProductImage> productImages;

}
