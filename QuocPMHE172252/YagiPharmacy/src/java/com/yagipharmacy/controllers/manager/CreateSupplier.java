/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.yagipharmacy.DAO.SupplierDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.entities.Supplier;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author author
 */
@WebServlet(name = "CreateSupplier", urlPatterns = {"/manager/CreateSupplier"})
public class CreateSupplier extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet CreateSupplier</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateSupplier at " + request.getContextPath() + "</h1>");
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
        if(userAuth==null){
            response.sendRedirect("../Login");
            return;
        }
        List<Long> roleList = Arrays.asList(2L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if(!checkAcpt){
            response.sendRedirect("../ErrorPage");
            return;
        }
        SupplierDAO pdao = new SupplierDAO();

        try {
            request.setAttribute("pl", pdao.fillterSupplier(""));
        } catch (SQLException ex) {
            Logger.getLogger(CreateSupplier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("./supplierAdd.jsp").forward(request, response);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String supplierCode = request.getParameter("supplierCode");
        String supplierName = request.getParameter("supplierName");
        String supplierCountryCode = request.getParameter("supplierCountryCode");
        String supplierPhone = request.getParameter("supplierPhone");
        String supplierEmail = request.getParameter("supplierEmail");
        boolean isDeleted = Boolean.parseBoolean(request.getParameter("isDeleted"));

        // Create a Supplier object
        Supplier newSupplier = new Supplier(supplierCode, supplierName, supplierCountryCode, supplierPhone, supplierEmail, isDeleted);

        // Call the DAO method to add the supplier
        SupplierDAO supplierDAO = new SupplierDAO();
        try {
            boolean isSuccess = supplierDAO.addNew(newSupplier);
            if (isSuccess) {
                response.sendRedirect("SupplierList");
            } else {
                response.sendRedirect("error.jsp");
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
