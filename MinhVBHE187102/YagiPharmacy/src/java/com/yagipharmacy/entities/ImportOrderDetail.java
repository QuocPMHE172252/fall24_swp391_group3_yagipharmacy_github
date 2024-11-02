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
public class ImportOrderDetail {
    private Long importOrderDetailId;
    private String batchCode;
    private Long importOrderId;
    private Long productId;
    private Long unitId;
    private Integer quantity;
    private Double importPrice;
    private Date importDate;
    private boolean isDeleted;
}
