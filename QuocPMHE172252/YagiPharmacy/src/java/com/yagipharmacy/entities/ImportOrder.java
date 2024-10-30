/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class ImportOrder {

    private Long importOrderId;
    private String importOrderCode;
    private Long createdBy;
    private Date createdDate;
    private Long approvedBy;
    private Date approvedDate;
    private Date importExpectedDate;
    private Boolean isAccepted;
    private String rejectedReason;
    private boolean isDeleted;
    private List<ImportOrderDetail> importOrderDetails;
    public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        return formatter.format(date);
    }
}
