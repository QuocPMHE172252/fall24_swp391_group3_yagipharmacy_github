/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

/**
 *
 * @author admin
 */
import java.util.Date;
import java.util.List;
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
public class SaleOrder {
    private Long saleOrderId;
    private Long orderBy;
    private String receiverName;
    private String receiverPhone;
    private String receiverEmail;
    private String province;
    private String district;
    private String commune;
    private String specificAddress;
    private Double totalPrice;
    private Date createdDate;
    private boolean isPaid;
    private boolean isDeleted;
    private Long processing;
    private List<SaleOrderDetail> saleOrderDetails;
}
