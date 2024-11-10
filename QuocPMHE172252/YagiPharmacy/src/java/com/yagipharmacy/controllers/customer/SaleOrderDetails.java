/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.customer;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.SaleOrderDAO;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductImage;
import com.yagipharmacy.entities.SaleOrder;
import com.yagipharmacy.entities.SaleOrderDetail;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "SaleOrderDetail", urlPatterns = {"/SaleOrderDetail"})
public class SaleOrderDetails extends HttpServlet {

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
            out.println("<title>Servlet SaleOrderDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleOrderDetail at " + request.getContextPath() + "</h1>");
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
        if (userAuth == null) {
            response.sendRedirect("Login");
            return;
        } else {
            String order_id = request.getParameter("order_id");
            SaleOrderDAO saleOrderDAO = new SaleOrderDAO();
            try {
                SaleOrder saleOrder = saleOrderDAO.getById(order_id);
                List<SaleOrderDetail> sales = saleOrder.getSaleOrderDetails();
                for (SaleOrderDetail sale : sales) {
                    sale.getProduct().setProductLongDesciption(null);
                }
                String saleOrderJson = new Gson().toJson(saleOrder);
                
                if(userAuth.getUserId()==saleOrder.getOrderBy()){
                    request.setAttribute("saleOrderJson", saleOrderJson);
                    request.getRequestDispatcher("SaleOrderDetail.jsp").forward(request, response);
                    return;
                } else{
                    response.sendRedirect("CustomerOrders");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
