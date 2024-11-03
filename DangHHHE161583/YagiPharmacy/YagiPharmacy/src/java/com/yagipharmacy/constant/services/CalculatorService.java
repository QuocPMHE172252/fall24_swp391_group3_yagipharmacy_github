/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.constant.services;

/**
 *
 * @author admin
 */
public class CalculatorService {
    public static Long parseLong(String n) {
        if(n==null){
            n ="0";
        }
        try {
            Long i = Long.parseLong(n.trim());
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static Double parseDouble(String n) {
        if(n==null){
            n ="0";
        }
        try {
            Double i = Double.parseDouble(n.trim());
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
