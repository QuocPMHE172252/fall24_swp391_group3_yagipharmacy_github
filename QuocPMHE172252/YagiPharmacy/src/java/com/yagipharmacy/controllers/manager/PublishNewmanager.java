/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.yagipharmacy.controllers.staff.*;
import com.yagipharmacy.DAO.NewsDAO;
import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.entities.News;
import com.yagipharmacy.entities.User;
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
@WebServlet(name = "PublishNewmanager", urlPatterns = {"/manager/PublishNew"})
public class PublishNewmanager extends HttpServlet {

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
            out.println("<title>Servlet PublishNew</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublishNew at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect("../staff/ListNews");
        } else {
            if (newsId.trim().equals("")) {
                response.sendRedirect("../staff/ListNews");
            } else {
                Long newsIdLong = CalculatorService.parseLong(newsId);
                NewsDAO newsDAO = new NewsDAO();
                try {
                    News findingNews = newsDAO.getById(newsIdLong + "");
                    if (findingNews.getNewsId() != 0) {
                        request.setAttribute("findingNews", findingNews);
                        request.getRequestDispatcher("PublishNew.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("./staff/ListNews");
                    }
                } catch (Exception e) {
                    response.sendRedirect("./staff/ListNews");
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
        String rejectReason = request.getParameter("rejectReason");
        String reject = request.getParameter("reject") == null ? "1" : request.getParameter("reject");
        String newsId = request.getParameter("newsId");
        String atuhorId = request.getParameter("atuhorId");
        NewsDAO newsDAO = new NewsDAO();
        System.out.println(rejectReason);
        System.out.println(reject);
        System.out.println("===============");
        System.out.println(atuhorId);
        User curentUser = (User) request.getSession().getAttribute("userAuth");
        try {
            News findingNews = new News();
            findingNews.setIsRejected(Integer.valueOf(reject));
            findingNews.setRejectedReason(rejectReason);
            findingNews.setUpdatedId(null);
            UserDAO udao = new UserDAO();
            User authorNew = udao.getById(atuhorId);
            String host = request.getServerName(); 
            int port = request.getServerPort();
            String subject = curentUser.getUserName() + (Integer.valueOf(reject)==3?" have been reject your news ":" have been aprove your news ");
            String url = "http://" + host + ":" + port + request.getContextPath() 
                     + "/staff/UpdateNews?newsId=" + newsId; 
            String body = "Cảm ơn đã đóng góp";
            new MailService().sentEmail(authorNew.getUserEmail(), subject, body);
            boolean check = newsDAO.updateRejectById(newsId, findingNews);
            News newsss = newsDAO.getById(newsId);
            request.setAttribute("findingNews", newsss);
            if (check) {
                request.setAttribute("change", true);
                doGet(request, response);
            } else {
                request.setAttribute("change", false);
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
