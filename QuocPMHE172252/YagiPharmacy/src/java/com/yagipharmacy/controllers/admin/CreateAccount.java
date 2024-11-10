/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.admin;

import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author author
 */
@WebServlet(name = "CreateAccount", urlPatterns = {"/admin/CreateAccount"})
public class CreateAccount extends HttpServlet implements AuthorizationService {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        if (userAuth == null) {
            response.sendRedirect("../Login");
            return;
        }
        List<Long> roleList = Arrays.asList(1L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if (!checkAcpt) {
            response.sendRedirect("../ErrorPage");
            return;
        }
        request.getRequestDispatcher("./accountAdd.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userName = request.getParameter("user_name");
            String userPhone = request.getParameter("user_phone");
            String userEmail = request.getParameter("user_email");
            String userPassword = request.getParameter("user_password");
            String userAvatar = request.getParameter("user_avatar");
            String specificAddress = request.getParameter("specific_address");
            String dateOfBirthString = request.getParameter("date_of_birth");
            String role_level = request.getParameter("user_role");
            String is_deleted = request.getParameter("is_deleted");
            String is_active = request.getParameter("is_active");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = dateFormat.parse(dateOfBirthString);
            UserDAO userDAO = new UserDAO();
            String messErrorEmail = "";
            String messErrorPhone = "";
            if (userDAO.getByEmail(userEmail) != null && userDAO.getByEmail(userEmail).getUserId() != 0) {
                messErrorEmail = "This email already exist in system. Try again!";
            }
            if (userDAO.getByPhone(userPhone) != null && userDAO.getByPhone(userPhone).getUserId() != 0) {
                messErrorPhone = "This phone number already exist in system. Try again!";
            }
            if (messErrorEmail != ""|| messErrorPhone != "") {
                request.setAttribute("messErrorPhone", messErrorPhone);
                request.setAttribute("messErrorEmail", messErrorEmail);
                request.getRequestDispatcher("./accountAdd.jsp").forward(request, response);
            } else {
                // Create a new User object
                User newUser = new User();
                newUser.setUserName(userName);
                newUser.setUserPhone(userPhone);
                newUser.setUserEmail(userEmail);
                newUser.setUserPassword(userPassword);
                newUser.setUserAvatar(userAvatar);
                newUser.setSpecificAddress(specificAddress);
                newUser.setDateOfBirth(dateOfBirth);
                newUser.setActiveCode("");
                newUser.setActive(is_active.equals("1"));
                newUser.setDeleted(is_deleted.equals("1"));
                newUser.setRoleLevel(Long.valueOf(role_level));
                newUser.setCreatedDate(new Date());
                // Call the addUser method from UserDAO
                boolean isAdded = false;

                isAdded = userDAO.addNew(newUser);
                if (isAdded) {
                    response.sendRedirect("./AccountList");
                } else {
                    request.setAttribute("errorMessage", "Failed to create account.");
                    request.getRequestDispatcher("./accountAdd.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, set an error message in the request
            request.setAttribute("errorMessage", "Failed to create account.");
            request.getRequestDispatcher("./accountAdd.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
