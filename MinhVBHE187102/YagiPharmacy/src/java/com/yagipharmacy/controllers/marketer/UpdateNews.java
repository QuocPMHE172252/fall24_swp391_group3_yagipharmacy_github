/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.marketer;

import com.yagipharmacy.DAO.NewsDAO;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.News;
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
@WebServlet(name = "UpdateNews", urlPatterns = {"/UpdateNews"})
public class UpdateNews extends HttpServlet {

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
            out.println("<title>Servlet UpdateNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNews at " + request.getContextPath() + "</h1>");
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
        String newsId = request.getParameter("newsId");
        if (newsId == null) {
            response.sendRedirect("ListNews");
        } else {
            if (newsId.trim().equals("")) {
                response.sendRedirect("ListNews");
            } else {
                Long newsIdLong = CalculatorService.parseLong(newsId);
                NewsDAO newsDAO = new NewsDAO();
                try {
                    News findingNews = newsDAO.getById(newsIdLong + "");
                    if (findingNews.getNewsId() != 0) {
                        request.setAttribute("findingNews", findingNews);
                        request.getRequestDispatcher("UpdateNews.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("ListNews");
                    }
                } catch (Exception e) {
                    response.sendRedirect("ListNews");
                }
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
        String base64_img = request.getParameter("base64_img");
        String news_content = request.getParameter("news_content");
        String newsId = request.getParameter("newsId");
        String is_deleted = request.getParameter("is_deleted");
        NewsDAO newsDAO = new NewsDAO();
        try {
            News findingNews = newsDAO.getById(newsId);
            if(findingNews.getNewsId()!=0){
                findingNews.setDeleted(is_deleted.equals("true"));
                findingNews.setNewsContent(news_content);
                findingNews.setNewsImage(base64_img);
                findingNews.setUpdatedId(null);
                boolean check = newsDAO.updateById(newsId, findingNews);
                if(check){
                    request.setAttribute("change", true);
                    doGet(request, response);
                } else {
                    request.setAttribute("change", false);
                    doGet(request, response);
                }
            }
        } catch (Exception e) {
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
