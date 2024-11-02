/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

/**
 *
 * @author admin
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderDetail {

    private Long orderDetailId;
    private Long saleOrderId;
    private Long productId;
    private Long unitId;
    private Long quantity;
    private String batchCode;
    private boolean isDeleted;
    private Product product;
    private Unit unit;
    
    
}
