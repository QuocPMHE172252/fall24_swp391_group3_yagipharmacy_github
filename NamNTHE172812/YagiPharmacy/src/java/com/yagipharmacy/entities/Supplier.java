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
public class Supplier {
    private Long supplierId;
    private String supplierCode;
    private String supplierName;
    private String supplierCountryCode;
    private String supplierPhone;
    private String supplierEmail;
    private boolean isDeleted;

    public Supplier(String supplierCode, String supplierName, String supplierCountryCode, String supplierPhone, String supplierEmail, boolean isDeleted) {
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.supplierCountryCode = supplierCountryCode;
        this.supplierPhone = supplierPhone;
        this.supplierEmail = supplierEmail;
        this.isDeleted = isDeleted;
    }
    
}
