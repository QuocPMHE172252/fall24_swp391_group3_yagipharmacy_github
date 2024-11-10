/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.staff;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.NewsCategoryDAO;
import com.yagipharmacy.DAO.NewsDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.News;
import com.yagipharmacy.entities.NewsCategory;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddNews", urlPatterns = {"/staff/AddNews"})
public class AddNews extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet AddNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNews at " + request.getContextPath() + "</h1>");
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
        List<Long> roleList = Arrays.asList(3L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if(!checkAcpt){
            response.sendRedirect("../ErrorPage");
            return;
        }
        List<NewsCategory> listParentCates = new ArrayList<>();
        List<NewsCategory> listChildCates = new ArrayList<>();
        NewsCategoryDAO newsCategoryDAO = new NewsCategoryDAO();
        try {
            Gson gson = new Gson();
            listParentCates = newsCategoryDAO.getAllParent();
            listChildCates = newsCategoryDAO.getAllChild();
            String listChildJson = gson.toJson(listChildCates);
            request.setAttribute("listParentCates", listParentCates);
            request.setAttribute("listChildJson", listChildJson);
            request.getRequestDispatcher("AddNews.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath()+"/ErrorPage");
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
        String is_deleted = request.getParameter("is_deleted");
        String parent_cate = request.getParameter("parent_cate");
        String cateId = request.getParameter("cateId");
        String title = request.getParameter("title");
        String hashtag = request.getParameter("hashtag");        
        User userAuth = (User)request.getSession().getAttribute("userAuth");
        NewsDAO newsDAO = new NewsDAO();
        try {
            News newsCreating = News.builder()
                    .createdDate(new Date())
                    .newsContent(news_content)
                    .newsImage(base64_img)
                    .newsCategoryId(CalculatorService.parseLong(cateId))
                    .creatorId(userAuth!=null?userAuth.getUserId():1L)
                    .isDeleted(is_deleted.equals("true"))
                    .newsTitle(title)
                    .newsHashtag(hashtag)
                    .updatedId(userAuth!=null?userAuth.getUserId():1L)
                    .build();
            
            boolean check = newsDAO.addNew(newsCreating);
            if(check){
                request.setAttribute("error", true);
                response.sendRedirect("ListNews");
            }
        } catch (Exception e) {
            request.setAttribute("error", false);
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
