/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.customer;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.SaleOrderDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.entities.SaleOrder;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "CustomerOrders", urlPatterns = {"/CustomerOrders"})
public class CustomerOrders extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet CustomerOrders</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerOrders at " + request.getContextPath() + "</h1>");
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
        User userAuth = (User)request.getSession().getAttribute("userAuth");
        List<Long> listAuth = Arrays.asList(1L,2L,3L,4L,5L);
        boolean checkAuth = acceptAuth(request, response, listAuth);
        if(checkAuth){
            SaleOrderDAO saleOrderDAO = new SaleOrderDAO();
            try {
                List<SaleOrder> saleOrders = saleOrderDAO.getByOrderBy(userAuth.getUserId()+"");
                String jsonSaleOrders = new Gson().toJson(saleOrders);
                request.setAttribute("jsonSaleOrders", jsonSaleOrders);
                request.getRequestDispatcher("CustomerOrders.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
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
        processRequest(request, response);
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
