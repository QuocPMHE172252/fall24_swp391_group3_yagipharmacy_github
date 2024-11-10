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
public class Staff {
    private Long staffId;
    private Long userId;
    private Long staffMajorId;
    private String staffEducation;
    private String staffExperience;
    private String staffDescription;
    private boolean gender;
    private boolean isDeleted;
    private String staffDegree;
    private MajorCategory staffMajor;
    private User user;
}
