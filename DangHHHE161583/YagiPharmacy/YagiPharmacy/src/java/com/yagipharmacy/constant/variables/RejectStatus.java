/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.constant.variables;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author author
 */
public enum RejectStatus {
    ADMIN(1, "Pending"),
    MANAGER(2, "Approved"),
    STAFF(3, "Rejected");

    private final int id;
    private final String roleName;

    RejectStatus(int id,  String roleName ) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RejectStatus getById(int id) {
        return Arrays.stream(RejectStatus.values())
                .filter(role -> role.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID: " + id));
    }

    public static List getList() {
        return Arrays.stream(UserRole.values())
                .map(role -> Map.of("id", role.getId(), "roleName", role.getRoleName()))
                .collect(Collectors.toList());
    }

    public static String getNameById(int id) {
        return getById(id).getRoleName();
    }
}
