/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.yagipharmacy.DAO.ImportOrderDAO;
import com.yagipharmacy.DAO.ImportOrderDetailDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.entities.ImportOrder;
import com.yagipharmacy.entities.ImportOrderDetail;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "ApproveImportOrder", urlPatterns = {"/manager/ApproveImportOrder"})
public class ApproveImportOrder extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet ApproveImportOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApproveImportOrder at " + request.getContextPath() + "</h1>");
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
        List<Long> roleList = Arrays.asList(2L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if(!checkAcpt){
            response.sendRedirect("../ErrorPage");
            return;
        }
        String status = request.getParameter("status");
        String import_order_id = request.getParameter("import_order_id");
        String reject_reason = request.getParameter("reject_reason");
        ImportOrderDAO importOrderDAO = new ImportOrderDAO();
        ImportOrderDetailDAO importOrderDetailDAO = new ImportOrderDetailDAO();
        try {
            ImportOrder findingImportOrder = importOrderDAO.getById(import_order_id);
            if (findingImportOrder.getImportOrderId() != 0) {
                findingImportOrder.setIsAccepted(status.equals("1"));
                findingImportOrder.setRejectedReason(reject_reason);
                findingImportOrder.setApprovedBy(1L);
                findingImportOrder.setApprovedDate(new Date());
                boolean check = importOrderDAO.updateById(import_order_id, findingImportOrder);
                if (check&&status.equals("1")) {
                    List<Long> supIdList = importOrderDetailDAO.getListSupplierIdByImportOrderId(import_order_id);
                    for (Long supId : supIdList) {
                        List<ImportOrderDetail> listOrderDetails = importOrderDetailDAO.getListByImportOrderIdAndSupplierId(import_order_id , supId + "");
                        String mailContent = MailService.createAcceptOrderDetailsMail(supId + "", findingImportOrder.getImportExpectedDate(), listOrderDetails);
                        MailService.sentEmail(listOrderDetails.get(0).getSupplier().getSupplierEmail(), "Đơn đặt hàng", mailContent);
                    }
                }
                response.sendRedirect("ImportOrderList");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ImportOrderList");
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
