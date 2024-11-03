/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.admin;

import com.yagipharmacy.DAO.MajorCategoryDAO;
import com.yagipharmacy.entities.MajorCategory;
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
 * @author Tunkyo
 */
@WebServlet(name = "MajorCategoryUpdate", urlPatterns = {"/admin/MajorCategoryUpdate"})
public class MajorCategoryUpdate extends HttpServlet {

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
            out.println("<title>Servlet MajorCategoryUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MajorCategoryUpdate at " + request.getContextPath() + "</h1>");
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
        MajorCategoryDAO mDao = new MajorCategoryDAO();
        String errorMessage = request.getParameter("errorMessage");
        request.setAttribute("errorMessage", errorMessage);
        try {
            //            request.setAttribute("ul", uDao.getUsers(search, status, index, 10));
            request.setAttribute("cl", mDao.getAll());
            request.setAttribute("cate", mDao.getById(request.getParameter("cid")));
        } catch (SQLException ex) {
            Logger.getLogger(MajorCategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MajorCategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("./majorCateUpdate.jsp").forward(request, response);
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
        MajorCategoryDAO majorcateDao = new MajorCategoryDAO();
        MajorCategory majorCate = MajorCategory.builder()
                .MajorCategoryId(Long.parseLong(request.getParameter("major_category_id")))
                .MajorCategoryDetail(request.getParameter("major_category_description"))
                .MajorCategoryName(request.getParameter("major_category_name"))
                .isDeleted(Boolean.parseBoolean(request.getParameter("is_deleted")))
                .build();

        try {
            boolean check = majorcateDao.updateById(String.valueOf(majorCate.getMajorCategoryId()), majorCate);
            if (check) {
                response.sendRedirect("majorCategoryList");
            } else {
                response.sendRedirect("MajorCategoryUpdate?errorMessage=dbId&cid=" + majorCate.getMajorCategoryId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("MajorCategoryUpdate?errorMessage=svErr&cid=" + majorCate.getMajorCategoryId());
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
