/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

/**
 *
 * @author admin
 */
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
    public String formatPrice() {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en", "US"));
        return formatter.format(this.totalPrice);
    }
    public String getProcess(){
        if(processing==0) return "Đang chờ";
        if(processing==1) return "Đã hủy";
        if(processing==2) return "Chuẩn bị hàng";
        if(processing==3) return "Đang giao";
        if(processing==4) return "Đã nhận";
        return "Đã hủy";
    }
}
