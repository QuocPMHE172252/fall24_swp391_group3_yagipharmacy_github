/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

import java.text.NumberFormat;
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
public class ProductUnit {
    private Long productUnitId;
    private Long parentId;
    private Long productId;
    private Long unitId;
    private Double sellPrice;
    private Long quantityPerUnit;
    private String productUnitName;
    private boolean canBeSold;
    private boolean isDeleted;
    private ProductUnit parentProductUnit;
    private Unit unit; 
        public String formatPrice() {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en", "US"));
        return formatter.format(this.sellPrice);
    }
}
