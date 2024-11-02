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

/**
 *
 * @author admin
 */
public interface AuthorizationService {

    default void loginRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        Long role = userAuth.getRoleLevel();
        if (role == SystemVariable.ADMIN) {
            response.sendRedirect(request.getContextPath()+"/admin/AdminDashboard");
        }
        if (role == SystemVariable.MANAGER) {
        }
        if (role == SystemVariable.STAFF) {
        }
        if (role == SystemVariable.CUSTOMER) {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}
