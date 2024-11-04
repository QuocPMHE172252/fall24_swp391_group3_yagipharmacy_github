/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.yagipharmacy.DAO.NewsCategoryDAO;
import com.yagipharmacy.DAO.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author author
 */
@WebServlet(name = "NewList", urlPatterns = {"/NewList"})
public class NewList extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewList at " + request.getContextPath() + "</h1>");
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String search = request.getParameter("search") == null ? "" : request.getParameter("search");
            String index = request.getParameter("index") == null ? "1" : request.getParameter("index");
            String categoryid = request.getParameter("categoryId") == null ? "" : request.getParameter("categoryId");
            NewsDAO newsDAO = new NewsDAO();
            NewsCategoryDAO newsCategoryDAO = new NewsCategoryDAO();
            int currentPage = Integer.valueOf(index);
            int pagesize = 9;
            int numberPage = 0;
            int totalCount = newsDAO.countList(search, categoryid);
            if (totalCount % 9 == 0) {
                numberPage = totalCount / 9;
            } else {
                numberPage = totalCount / 9 + 1;
            }
            request.setAttribute("index", index);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("cate", newsCategoryDAO.getAll());
            request.setAttribute("list", newsDAO.getList(search, categoryid, currentPage, pagesize));
            request.setAttribute("bloList", newsDAO.getTop5new());

            request.getRequestDispatcher("newslist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
