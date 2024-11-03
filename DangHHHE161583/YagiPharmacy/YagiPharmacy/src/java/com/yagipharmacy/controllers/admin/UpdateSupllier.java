/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.admin;

import com.yagipharmacy.DAO.SupplierDAO;
import com.yagipharmacy.entities.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author author
 */
@WebServlet(name = "UpdateSupllier", urlPatterns = {"/admin/UpdateSupllier"})
public class UpdateSupllier extends HttpServlet {

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
            out.println("<title>Servlet UpdateSupllier</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSupllier at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the supplier ID from the request
        String supplierId = request.getParameter("id");

        SupplierDAO supplierDAO = new SupplierDAO();
        Supplier supplier = null;
        try {
            supplier = supplierDAO.getById(supplierId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("pl", supplierDAO.fillterSupplier(""));
        } catch (java.sql.SQLException ex) {
            Logger.getLogger(CreateSupplier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Set the supplier object as a request attribute to be accessible in the JSP
        request.setAttribute("supplier", supplier);
        request.getRequestDispatcher("./supplierUpdate.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch form parameters
        String supplierId = request.getParameter("supplierId");
        String supplierCode = request.getParameter("supplierCode");
        String supplierName = request.getParameter("supplierName");
        String supplierCountryCode = request.getParameter("supplierCountryCode");
        String supplierPhone = request.getParameter("supplierPhone");
        String supplierEmail = request.getParameter("supplierEmail");
        boolean isDeleted = Boolean.parseBoolean(request.getParameter("isDeleted"));

        // Create Supplier object with updated values
        Supplier updatedSupplier = Supplier.builder()
                .supplierCode(supplierCode)
                .supplierName(supplierName)
                .supplierCountryCode(supplierCountryCode)
                .supplierPhone(supplierPhone)
                .supplierEmail(supplierEmail)
                .isDeleted(isDeleted)
                .build();

        // Use DAO to update supplier in the database
        SupplierDAO supplierDAO = new SupplierDAO();
        try {
            boolean isUpdated = supplierDAO.updateById(supplierId, updatedSupplier);
            if (isUpdated) {
                response.sendRedirect("SupplierList"); // Redirect to success page
            } else {
                response.sendRedirect("error.jsp"); // Redirect to error page
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
