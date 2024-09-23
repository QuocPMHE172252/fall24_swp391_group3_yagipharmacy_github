/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.yagipharmacy.DAO.ProductCategoryDAO;
import com.yagipharmacy.entities.ProductCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author author
 */
@WebServlet(name = "CategoryAdd", urlPatterns = {"/manager/CategoryAdd"})
public class CategoryAdd extends HttpServlet {

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
            out.println("<title>Servlet CategoryAdd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryAdd at " + request.getContextPath() + "</h1>");
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
        ProductCategoryDAO uDao = new ProductCategoryDAO();
        try {
            //            request.setAttribute("ul", uDao.getUsers(search, status, index, 10));
            request.setAttribute("cl", uDao.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("./productCateAdd.jsp").forward(request, response);

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
        // Get form parameters
        String categoryCode = request.getParameter("productCategoryCode");
        String categoryName = request.getParameter("productCategoryName");
        String categoryDetail = request.getParameter("productCategoryDetail");
        String parentCategoryIdStr = request.getParameter("productCategoryParentId");
        String categoryLevelStr = request.getParameter("productCategoryLevel");
        String isDeletedStr = request.getParameter("isDeleted");

        // Convert fields to appropriate types
        Long parentCategoryId = parentCategoryIdStr == null || parentCategoryIdStr.isEmpty() ? null : Long.parseLong(parentCategoryIdStr);
        Long categoryLevel = Long.parseLong(categoryLevelStr);
        boolean isDeleted = Boolean.parseBoolean(isDeletedStr);

        // Create ProductCategory object
        ProductCategory category = ProductCategory.builder()
                .productCategoryCode(categoryCode)
                .productCategoryName(categoryName)
                .productCategoryDetail(categoryDetail)
                .productCategoryParentId(parentCategoryId)
                .productCategoryLevel(categoryLevel)
                .isDeleted(isDeleted)
                .build();

        // Save to the database
        ProductCategoryDAO categoryDAO = new ProductCategoryDAO();
        try {
            if (categoryDAO.addNew(category)) {
                response.sendRedirect("./CategoryList"); // Redirect to a success page or category listing page
            } else {
                request.setAttribute("errorMessage", "Error adding category. Please try again.");
                request.getRequestDispatcher("./productCateAdd.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("./productCateAdd.jsp").forward(request, response);
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
