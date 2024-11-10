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
    private Long deleteProcess;
    private String productLongDesciption;
    private ProductCategory productCategory;
    private Supplier supplier;
    private User author;
    private List<ProductExcipient> productExcipients;
    private List<ProductUnit> productUnits;
    private List<ProductImage> productImages;
    
    public ProductUnit getLastUnit(){
        ProductUnit productUnit = ProductUnit.builder()
                .canBeSold(true)
                .isDeleted(false)
                .sellPrice(0.0)
                .build();
        for (ProductUnit pUnit : productUnits) {
            productUnit = pUnit;
        }
        return productUnit;
    }

    public Product coppyNewProduct() {
        return new Product(productId, productCode, authorId, productCategoryId, productCountryCode, brand, productTarget, productName, dosageForm, productSpecification, productDescription, createdDate, isPrescription, isDeleted,deleteProcess, productLongDesciption, productCategory, supplier, author, productExcipients, productUnits, productImages);
    }
    

}
