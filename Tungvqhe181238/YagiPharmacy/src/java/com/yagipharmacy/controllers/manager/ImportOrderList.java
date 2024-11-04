/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.yagipharmacy.DAO.ImportOrderDAO;
import com.yagipharmacy.entities.ImportOrder;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "ImportOrderList", urlPatterns = {"/manager/ImportOrderList"})
public class ImportOrderList extends HttpServlet {

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
            out.println("<title>Servlet ImportOrderList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImportOrderList at " + request.getContextPath() + "</h1>");
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
        ImportOrderDAO importOrderDAO = new ImportOrderDAO();
        List<ImportOrder> dataAll = new ArrayList<>();
        List<ImportOrder> data = new ArrayList<>();
        try {
            dataAll = importOrderDAO.getAll();
            String reject_status = request.getParameter("reject_status");
            String delete_status = request.getParameter("delete_status");
            if (reject_status == null) {
                reject_status = "all";
            }
            if (delete_status == null) {
                delete_status = "all";
            }
            List<ImportOrder> tempData = getByAccept(dataAll, reject_status);
            data = getByDeleted(tempData, delete_status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("data", data);
        String created = request.getParameter("created");
        if (created != null) {
            request.setAttribute("created", created);
        }

        request.getRequestDispatcher("ImportOrderList.jsp").forward(request, response);
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

    public List<ImportOrder> getByAccept(List<ImportOrder> inputData, String target) {
        boolean checkEq = target.equals("all");
        List<ImportOrder> list = new ArrayList<>();
        if (checkEq) {
            return inputData;
        }
        Boolean isAccepted = null;
        if (target.equals("0")) {
            isAccepted = null;
        }
        if (target.equals("1")) {
            isAccepted = true;
        }
        if (target.equals("2")) {
            isAccepted = false;
        }
        for (ImportOrder importOrder : inputData) {
            if (importOrder == null) {
                if (isAccepted == null) {
                    list.add(importOrder);
                    continue;
                }
            } else {
                if (importOrder.getIsAccepted() == isAccepted) {
                    list.add(importOrder);
                    continue;
                }
            }
        }
        return list;
    }

    public List<ImportOrder> getByDeleted(List<ImportOrder> inputData, String target) {
        List<ImportOrder> list = new ArrayList<>();
        if (target.equals("all")) {
            return inputData;
        }
        for (ImportOrder importOrder : inputData) {
            if (importOrder.isDeleted() == target.equals("1")) {
                list.add(importOrder);
                continue;
            }
        }
        return list;
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
