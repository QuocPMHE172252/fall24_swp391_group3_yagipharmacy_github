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

/**
 *
 * @author admin
 */
@WebServlet(name="ApproveImportOrder", urlPatterns={"/manager/ApproveImportOrder"})
public class ApproveImportOrder extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ApproveImportOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApproveImportOrder at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String status = request.getParameter("status");
        String import_order_id = request.getParameter("import_order_id");
        String reject_reason = request.getParameter("reject_reason");
        ImportOrderDAO importOrderDAO = new ImportOrderDAO();
        try {
            ImportOrder findingImportOrder = importOrderDAO.getById(import_order_id);
            if(findingImportOrder.getImportOrderId()!=0){
                findingImportOrder.setIsAccepted(status.equals("1"));
                findingImportOrder.setRejectedReason(reject_reason);
                findingImportOrder.setApprovedBy(1L);
                boolean check = importOrderDAO.updateById(import_order_id, findingImportOrder);
                response.sendRedirect("ImportOrderList");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ImportOrderList");
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
