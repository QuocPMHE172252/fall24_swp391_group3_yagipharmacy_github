/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.admin;

import com.yagipharmacy.DAO.ProductCategoryDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.entities.ProductCategory;
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
@WebServlet(name = "CategoryUpdate", urlPatterns = {"/admin/CategoryUpdate"})
public class CategoryUpdate extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet CategoryUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryUpdate at " + request.getContextPath() + "</h1>");
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
        List<Long> roleList = Arrays.asList(3L,2L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if(!checkAcpt){
            response.sendRedirect("../ErrorPage");
            return;
        }
        ProductCategoryDAO uDao = new ProductCategoryDAO();
        String errorMessage = request.getParameter("errorMessage");
        request.setAttribute("errorMessage", errorMessage);
        try {
            //            request.setAttribute("ul", uDao.getUsers(search, status, index, 10));
            request.setAttribute("cl", uDao.getAll());
            request.setAttribute("cate", uDao.getById(request.getParameter("cid")));
        } catch (SQLException ex) {
            Logger.getLogger(CategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("./productCateUpdate.jsp").forward(request, response);

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
        User userAuth = (User)request.getSession().getAttribute("userAuth");
        if(userAuth==null){
            response.sendRedirect("../Login");
            return;
        }
        List<Long> roleList = Arrays.asList(3L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if(!checkAcpt){
            response.sendRedirect("../ErrorPage");
            return;
        }
        String parentCategoryIdStr = request.getParameter("product_category_parent_id");
        Long parentCategoryId = parentCategoryIdStr == null || parentCategoryIdStr.isEmpty() ? null : Long.parseLong(parentCategoryIdStr);
        ProductCategoryDAO categoryDao = new ProductCategoryDAO();
        ProductCategory category = ProductCategory.builder()
                .productCategoryId(Long.parseLong(request.getParameter("product_category_id")))
                .productCategoryParentId(parentCategoryId)
                .productCategoryLevel(Long.parseLong(request.getParameter("product_category_level")))
                .productCategoryCode(request.getParameter("product_category_code"))
                .productCategoryName(request.getParameter("product_category_name"))
                .productCategoryDetail(request.getParameter("product_category_detail"))
                .isDeleted(Boolean.parseBoolean(request.getParameter("is_deleted")))
                .build();

        try {
            boolean check = categoryDao.updateById(String.valueOf(category.getProductCategoryId()), category);
            if (check) {
                response.sendRedirect("CategoryList"); // Redirect to category list page after update
            } else {
                response.sendRedirect("CategoryUpdate?errorMessage=dbId&cid=" + category.getProductCategoryId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CategoryUpdate?errorMessage=svErr&cid=" + category.getProductCategoryId());
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
