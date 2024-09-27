/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.yagipharmacy.constant.variables;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author author
 */

public enum UserRole {
    ADMIN(1L, "Admin"),
    MANAGER(2L, "Manager"),
    STAFF(3L, "Staff"),
    MARKETER(4L,"Marketer"),
    CUSTOMER(5L, "Customer");

    private final Long id;
    private final String roleName;

    UserRole(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRole getById(Long id) {
        return Arrays.stream(UserRole.values())
                     .filter(role -> role.getId() == id)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Invalid role ID: " + id));
    }

    // Method to get list of roles with key-value pairs (ID and role name)
    public static List getRolesList() {
        return Arrays.stream(UserRole.values())
                     .map(role -> Map.of("id", role.getId(), "roleName", role.getRoleName()))
                     .collect(Collectors.toList());
    }

    // You can also add this method to get role name by ID
    public static String getRoleNameById(Long id) {
        return getById(id).getRoleName();
    }
}