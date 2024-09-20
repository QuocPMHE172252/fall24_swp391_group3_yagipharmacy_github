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
    private int productCategoryId;
    private String productCountryCode;
    private Long supplierId;
    private String productTarget;
    private String productName;
    private String dosageForm;
    private String productSpecification;
    private String productExcipient;
    private String productDescription;
    private Date createdDate;
    private boolean isDeleted;
}
