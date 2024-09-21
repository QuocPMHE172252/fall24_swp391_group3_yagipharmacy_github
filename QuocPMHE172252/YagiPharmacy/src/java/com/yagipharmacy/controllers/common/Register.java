/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.constant.services.RandomService;
import com.yagipharmacy.constant.variables.SystemVariable;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author admin
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        request.getRequestDispatcher("Register.jsp").forward(request, response);
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
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String activeCode = RandomService.getRandomActiveCode(10L);
        User newUser = User.builder()
                .userId(0L)
                .userName(fullname)
                .userPhone(phone)
                .userEmail(email)
                .userPassword(password)
                .roleLevel(SystemVariable.CUSTOMER)
                .createdDate(new Date())
                .isActive(false)
                .isDeleted(false)
                .activeCode(activeCode)
                .build();
        UserDAO userDAO = new UserDAO();
        try {
            boolean check = userDAO.addNew(newUser);
            if (check) {
                String verifyLink = "http://localhost:9999/YagiPharmacy/Verify?email=" + email;
                String tagLink = "<a href='" + verifyLink + "'>Click here</a><br>" + "<h2>Verify Code: " + activeCode + "</h2>";
                boolean sendMailSuccess = MailService.sentEmail(email, "Click to below link to verify your account", tagLink);
                response.sendRedirect("Login?register=success");
            } else {
                User findingUser = userDAO.getUserByPhone(phone);
                if (findingUser.getUserId() != 0) {
                    request.setAttribute("phoneError", "Số điện thoại đã được sử dụng");
                }
                findingUser = userDAO.getUserByEmail(email);
                if (findingUser.getUserId() != 0) {
                    request.setAttribute("emailError", "Email đã được sử dụng");
                }
                request.setAttribute("fullname", fullname);
                request.setAttribute("phone", phone);
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("systemError", "Hệ thống gặp sự cố vui lòng thử lại sau");
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
