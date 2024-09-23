/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.entities;

import com.yagipharmacy.constant.variables.UserRole;
import java.util.Date;
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
public class User {
    private Long userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userPassword;
    private Long roleLevel;
    private String userAvatar;
    private String userBank;
    private String userBankCode;
    private String userProvince;
    private String userDistrict;
    private String userCommune;
    private String specificAddress;
    private String createdDate;
    private String dateOfBirth;
    private String activeCode;
    private boolean isActive;
    private boolean isDeleted;
    private String roleName;
    
    public String getRoleNamee(){
        return  UserRole.getRoleNameById(roleLevel);
    }
}
