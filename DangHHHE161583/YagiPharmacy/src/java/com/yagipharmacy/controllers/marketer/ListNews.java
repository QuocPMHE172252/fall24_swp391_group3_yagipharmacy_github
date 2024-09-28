/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.marketer;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.NewsCategoryDAO;
import com.yagipharmacy.DAO.NewsDAO;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.News;
import com.yagipharmacy.entities.NewsCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "ListNews", urlPatterns = {"/ListNews"})
public class ListNews extends HttpServlet {

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
            out.println("<title>Servlet ListNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListNews at " + request.getContextPath() + "</h1>");
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
        String cateId = request.getParameter("cateId");
        String title = request.getParameter("title");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String hashtag = request.getParameter("hashtag");
        if (cateId == null) {
            cateId = "0";
        }
        if (cateId.equals("all")) {
            cateId = "0";
        }
        if (title == null) {
            title = "all";
        }else {
            if(title.trim() == ""){
                title = "all";
            }
        }
        if (from == null||from == "") {
            from = "all";
        }
        if (to == null||to == "") {
            to = "all";
        }
        if (hashtag == null) {
            hashtag = "all";
        }else {
            if(hashtag.trim() == ""){
                hashtag = "all";
            }
        }

        NewsDAO newsDAO = new NewsDAO();
        NewsCategoryDAO newsCategoryDAO = new NewsCategoryDAO();
        Long cid = CalculatorService.parseLong(cateId);
        List<News> data = new ArrayList<>();
        List<NewsCategory> listParentCates = new ArrayList<>();
        List<NewsCategory> listChildCates = new ArrayList<>();
        try {
            Date fromDate = new Date();
            Date toDate = new Date();
            if (from.equals("all") || to.equals("all")) {
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                fromDate = dateFormat.parse(from);
                toDate = dateFormat.parse(to);
            }

            List<News> originList = newsDAO.getAll();
            for (News news : originList) {
                boolean check = true;
                if (!(cid == 0 || news.getNewsCategoryId() == cid)) {
                    check = false;
                }
                if (!(title.equals("all") || news.getNewsTitle().contains(title))) {
                    check = false;
                }
                Date newsDate = news.getCreatedDate();
                if (!((newsDate.after(fromDate) || newsDate.equals(fromDate))
                        && (newsDate.before(toDate) || newsDate.equals(toDate)))) {
                    check = false;
                    if (from.equals("all") || to.equals("all")) {
                        check = true;
                    }
                }
                if (!(hashtag.equals("all") || checkHashTag(hashtag, news.getNewsHashtag()))) {
                    check = false;
                }

                if (check) {
                    data.add(news);
                }
            }
            Gson gson = new Gson();
            listParentCates = newsCategoryDAO.getAllParent();
            listChildCates = newsCategoryDAO.getAllChild();
            String listChildJson = gson.toJson(listChildCates);
            request.setAttribute("listParentCates", listParentCates);
            request.setAttribute("listChildJson", listChildJson);
        } catch (Exception e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }
        request.setAttribute("data", data);
        request.getRequestDispatcher("ListNews.jsp").forward(request, response);
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

    public boolean checkHashTag(String tag, String listTags) {
        String[] tagArr = listTags.split("#");
        int lenght = tagArr.length;
        for (int i = 0; i < lenght; i++) {
            if (tag.equals(tagArr[i])) {
                return true;
            }
        }
        return false;
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
