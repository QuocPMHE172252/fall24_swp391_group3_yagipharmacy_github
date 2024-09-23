/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.yagipharmacy.DAO.UserDAO;
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
@WebServlet(name = "Verify", urlPatterns = {"/Verify"})
public class Verify extends HttpServlet {

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
            out.println("<title>Servlet Verify</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Verify at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        if (email == null) {
            email = "";
        }
        UserDAO userDAO = new UserDAO();
        try {
            User findingUser = User.builder()
                    .userId(0L)
                    .build();
            findingUser = userDAO.getUserByEmail(email);
            if (findingUser.getUserId() == 0 || findingUser.isActive() == true || findingUser.isDeleted() == true) {
                response.sendRedirect("ErrorPage");
            } else {
                request.setAttribute("email", email);
                request.getRequestDispatcher("Verify.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ErrorPage");
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
        String email = request.getParameter("email");
        String activeCode = request.getParameter("activecode");
        try {
            UserDAO userDAO = new UserDAO();
            User findingUser = userDAO.getUserByEmail(email);
            if (findingUser.getUserId() != 0 && findingUser.isActive() == false && findingUser.isDeleted() == false && findingUser.getActiveCode().equals(activeCode)) {
                findingUser.setActive(true);
                boolean check = userDAO.updateById(findingUser.getUserId() + "", findingUser);
                if (check) {
                    response.sendRedirect("Login");
                } else {
                    response.sendRedirect("ErrorPage");
                }
            } else {
                request.setAttribute("error", "Bạn đã nhập sai mã kích hoạt hoặc đường dẫn hết hạn!");
                request.setAttribute("activecode", activeCode);
                doGet(request, response);
            }
        } catch (Exception e) {
            String error = "Có gì đó sai!";
            response.sendRedirect("ErrorPage?error=" + error);
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
