/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.customer;

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
 * @author admin
 */
@WebServlet(name = "ChangeProfile", urlPatterns = {"/ChangeProfile"})
public class ChangeProfile extends HttpServlet {

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
            out.println("<title>Servlet ChangeProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeProfile at " + request.getContextPath() + "</h1>");
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

            // Định dạng chuỗi theo "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(userAuth.getDateOfBirth());
            request.setAttribute("formattedDate", formattedDate);
            request.getRequestDispatcher("ChangeProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("Login");
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
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dob");
        String user_province = request.getParameter("user_province");
        String user_district = request.getParameter("user_district");
        String user_commune = request.getParameter("user_commune");
        String specific_address = request.getParameter("specific_address");
        String base64_img = request.getParameter("base64_img");
        UserDAO userDAO = new UserDAO();
        try {
            User findingUser = userDAO.getById(userAuth.getUserId() + "");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = dateFormat.parse(dob);
            findingUser.setUserName(fullname);
            findingUser.setUserAvatar(base64_img);
            findingUser.setUserProvince(user_province);
            findingUser.setUserDistrict(user_district);
            findingUser.setUserCommune(user_commune);
            findingUser.setSpecificAddress(specific_address);
            findingUser.setDateOfBirth(dateOfBirth);
            boolean check = userDAO.updateById(findingUser.getUserId() + "", findingUser);
            if (check) {
                request.getSession().setAttribute("userAuth", findingUser);
                request.setAttribute("change", true);
                doGet(request, response);
            } else {
                request.setAttribute("change", false);
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("change", false);
            doGet(request, response);
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
