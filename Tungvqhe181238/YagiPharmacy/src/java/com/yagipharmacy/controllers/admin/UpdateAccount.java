/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.admin;

import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author author
 */
@WebServlet(name = "UpdateAccount", urlPatterns = {"/admin/UpdateAccount"})
public class UpdateAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = request.getParameter("error");
        if(error!=null){
            request.setAttribute("errorMessage", "Email hoặc số điện thoại đã được sử dụng");
        }
        try {
            User user = new UserDAO().getById(request.getParameter("uid"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(user.getDateOfBirth());
            request.setAttribute("formattedDate", formattedDate);
            request.setAttribute("user", user);
            request.getRequestDispatcher("./accountUpdate.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Long userId = Long.parseLong(request.getParameter("user_id"));
        String userName = request.getParameter("user_name");
        String userPhone = request.getParameter("user_phone");
        String userEmail = request.getParameter("user_email");
        String userPassword = request.getParameter("user_password");
        String userBank = request.getParameter("user_bank");
        String userBankCode = request.getParameter("user_bank_code");
        String specificAddress = request.getParameter("specific_address");
        String dateOfBirthString = request.getParameter("date_of_birth");
        try {
            // Retrieve form data

            boolean isActive = "1".equals(request.getParameter("is_active"));
            boolean isDeleted = "1".equals(request.getParameter("is_deleted"));
            Long roleLevel = Long.parseLong(request.getParameter("user_role"));

            // Handle avatar upload (Base64 encoded or save the file to the server)
            String userAvatar = request.getParameter("user_avatar");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = dateFormat.parse(dateOfBirthString);
            UserDAO userDAO = new UserDAO();
            // Create User object
            User findingUser = userDAO.getById(userId + "");
            findingUser.setUserName(userName);
            findingUser.setUserPhone(userPhone);
            findingUser.setUserEmail(userEmail);
            findingUser.setUserPassword(userPassword);
//            findingUser.setUserBank(userBank);
//            findingUser.setUserBankCode(userBankCode);
            findingUser.setSpecificAddress(specificAddress);
            findingUser.setDateOfBirth(dateOfBirth);
            findingUser.setActive(isActive);
            findingUser.setDeleted(isDeleted);
            findingUser.setRoleLevel(roleLevel);
            findingUser.setUserAvatar(userAvatar);

            // Call the UserDAO to update the user in the database
            boolean success = userDAO.updateById(request.getParameter("user_id"), findingUser);

            if (success) {
                response.sendRedirect("./AccountList");
            } else {
                // Handle the error (e.g., show an error message)
                request.setAttribute("errorMessage", "Email hoặc số điện thoại đã được sử dụng");
                request.setAttribute("user", findingUser);
                request.getRequestDispatcher("./accountUpdate.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("UpdateAccount?error=true&uid="+userId);
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
