/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.constant.services;

import com.yagipharmacy.constant.variables.SystemVariable;
import com.yagipharmacy.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author admin
 */
public interface AuthorizationService {

    default void loginRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        Long role = userAuth.getRoleLevel();
        if (role == SystemVariable.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/HomePage");
            return;
        }
        if (role == SystemVariable.MANAGER) {
            response.sendRedirect("HomePage");
            return;
        }
        if (role == SystemVariable.STAFF) {
            response.sendRedirect("HomePage");
            return;
        }
        if (role == SystemVariable.CUSTOMER) {
            response.sendRedirect("HomePage");
            return;
        }
    }

    default boolean acceptAuth(HttpServletRequest request, HttpServletResponse response, List<Long> roleListAccept) throws ServletException, IOException {
        User userAuth = (User)request.getSession().getAttribute("userAuth");
        boolean isAccept= false; 
        if(userAuth==null){
            return false;
        } else{
            for (Long roleLv : roleListAccept) {
                if(userAuth.getRoleLevel()==roleLv){
                    isAccept = true;
                    break;
                }
            }
        }
        return isAccept;
    }
}
