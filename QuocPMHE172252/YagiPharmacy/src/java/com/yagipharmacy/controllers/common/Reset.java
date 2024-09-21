/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.constant.services.RandomService;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author admin
 */
@WebServlet(name = "Reset", urlPatterns = {"/Reset"})
public class Reset extends HttpServlet implements AuthorizationService {

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
            out.println("<title>Servlet Reset</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reset at " + request.getContextPath() + "</h1>");
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
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        if (userAuth != null) {
            loginRedirect(request, response);
        } else {
            request.getRequestDispatcher("Reset.jsp").forward(request, response);
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
        String emailPhone = request.getParameter("emailPhone");
        if (emailPhone == null) {
            emailPhone = "";
        }
        UserDAO userDAO = new UserDAO();
        try {
            User findingUser = userDAO.getUserByPhone(emailPhone);
            if (findingUser.getUserId() == 0L) {
                findingUser = userDAO.getUserByEmail(emailPhone);
            }
            if (findingUser.getUserId() != 0L && !findingUser.isDeleted()) {
                if (findingUser.getUserEmail() != null) {
                    String activeCode = RandomService.getRandomActiveCode(10L);
                    findingUser.setActive(false);
                    findingUser.setActiveCode(activeCode);
                    boolean check = userDAO.updateById(findingUser.getUserId() + "", findingUser);
                    if (check) {
                        String verifyLink = "http://localhost:9999/YagiPharmacy/ResetAccept?email=" + findingUser.getUserEmail();
                        String tagLink = "<h2>Verify Code: " + activeCode + "</h2>";
                        boolean sendMailSuccess = MailService.sentEmail(findingUser.getUserEmail(), "Your active code", tagLink);
                        response.sendRedirect("Login?register=success");
                    }
                } else {
                    request.setAttribute("error", "Tài khoản chưa cập nhật email");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("error", "Tài khoản không tồn tại");
                doGet(request, response);
            }
        } catch (Exception e) {
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
