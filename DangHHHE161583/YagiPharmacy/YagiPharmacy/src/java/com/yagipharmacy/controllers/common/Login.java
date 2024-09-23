/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.variables.SystemVariable;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet implements AuthorizationService {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        String register = request.getParameter("register");
        String reset = request.getParameter("reset");
        if (register != null) {
            if (register.equals("success")) {
                request.setAttribute("notice", "Đăng kí thành công vui lòng kiểm tra email để xác thực");
            }
        }
        if(reset!=null){
            if (reset.equals("success")) {
                request.setAttribute("notice", "Thay đổi mật khẩu thành công vui lòng đăng nhập");
            }
        }
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        if (userAuth != null) {
            loginRedirect(request, response);
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        String password = request.getParameter("password");
        request.setAttribute("emailPhone", emailPhone);
        request.setAttribute("password", password);
        if (emailPhone == null) {
            emailPhone = "";
        }
        if (password == null) {
            password = "";
        }
        UserDAO userDAO = new UserDAO();
        try {

            User findingUser = userDAO.getUserByEmail(emailPhone);
            if (findingUser.getUserId() == 0) {
                findingUser = userDAO.getUserByPhone(emailPhone);
            }
            if (findingUser.getUserId() != 0 && findingUser.getUserPassword().equals(password)) {
                if (findingUser.isDeleted()) {
                    request.setAttribute("error", "Tài khoản đã bị khóa vĩnh viễn");
                    doGet(request, response);
                } else if (!findingUser.isActive()) {
                    request.setAttribute("error", "Tài khoản chưa được kích hoạt");
                    doGet(request, response);
                } else {
                    request.getSession().setAttribute("userAuth", findingUser);
                    loginRedirect(request, response);
                }
            } else {
                request.setAttribute("error", "Tài khoản hoặc mật khẩu sai");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
