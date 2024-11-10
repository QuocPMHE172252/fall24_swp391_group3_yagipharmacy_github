/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.ImportOrderDAO;
import com.yagipharmacy.DAO.ImportOrderDetailDAO;
import com.yagipharmacy.DAO.StockDAO;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.ImportOrder;
import com.yagipharmacy.entities.ImportOrderDetail;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.entities.Stock;
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
@WebServlet(name = "UpdateImportOrder", urlPatterns = {"/manager/UpdateImportOrder"})
public class UpdateImportOrder extends HttpServlet {

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
            out.println("<title>Servlet UpdateImportOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateImportOrder at " + request.getContextPath() + "</h1>");
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
        String imp_id = request.getParameter("imp_id");
        try {
            ImportOrder importOrder = new ImportOrderDAO().getById(imp_id);
            for (ImportOrderDetail importOrderDetail : importOrder.getImportOrderDetails()) {
                importOrderDetail.getProduct().setProductLongDesciption(null);
            }
            String dataImportOrder = new Gson().toJson(importOrder);
            request.setAttribute("dataImportOrder", dataImportOrder);
            request.getRequestDispatcher("UpdateImportOrder.jsp").forward(request, response);
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
        String submit_import_detail_id = request.getParameter("submit_import_detail_id");
        String submit_processing = request.getParameter("submit_processing");
        String submit_batch_code = request.getParameter("submit_batch_code");
        String submit_import_price = request.getParameter("submit_import_price");
        String submit_import_date = request.getParameter("submit_import_date");
        String submit_MFG_date = request.getParameter("submit_MFG_date");
        String submit_EXP_date = request.getParameter("submit_EXP_date");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ImportOrderDetailDAO importOrderDetailDAO = new ImportOrderDetailDAO();
        Long imp_id = 0L;
        try {
            Long processing = CalculatorService.parseLong(submit_processing);
            Double importPrice = CalculatorService.parseDouble(submit_import_price);
            Date importDate = formatter.parse(submit_import_date);

            ImportOrderDetail findingImportOrderDetail = importOrderDetailDAO.getById(submit_import_detail_id);
            imp_id = findingImportOrderDetail.getImportOrderId();
            findingImportOrderDetail.setProcessing(processing);
            findingImportOrderDetail.setBatchCode(submit_batch_code);
            findingImportOrderDetail.setImportPrice(importPrice);
            findingImportOrderDetail.setImportDate(importDate);

            boolean check = importOrderDetailDAO.updateById(submit_import_detail_id, findingImportOrderDetail);
            if (check) {

                if (processing == 4) {
                    Date mfgDate = formatter.parse(submit_MFG_date);
                    Date expDate = formatter.parse(submit_EXP_date);
                    StockDAO stockDAO = new StockDAO();
                    List<Stock> findingStocks = stockDAO.getByBatchCode(submit_batch_code);
                    if (findingStocks.size() == 0L) {
                        Stock newStock = Stock.builder()
                                .batchCode(submit_batch_code)
                                .quantity(findingImportOrderDetail.getQuantity())
                                .productId(findingImportOrderDetail.getProductId())
                                .unitId(findingImportOrderDetail.getUnitId())
                                .mfgDate(mfgDate)
                                .expDate(expDate)
                                .isDeleted(false)
                                .build();
                        boolean checkAddStock = stockDAO.addNew(newStock);
                        List<ProductUnit> findingProductUnits = findingImportOrderDetail.getProduct().getProductUnits();
                        for (ProductUnit findingProductUnit : findingProductUnits) {
                            if(findingImportOrderDetail.getProductId()==findingProductUnit.getProductId()&&findingImportOrderDetail.getUnitId()==findingProductUnit.getUnitId()){
                                continue;
                            }
                            Stock otherStock = Stock.builder()
                                    .batchCode(submit_batch_code)
                                    .quantity(0L)
                                    .productId(findingImportOrderDetail.getProductId())
                                    .unitId(findingImportOrderDetail.getUnitId())
                                    .mfgDate(mfgDate)
                                    .expDate(expDate)
                                    .build();
                            stockDAO.addNew(otherStock);
                        }
                    } else {
                        boolean checkNotEx = true;
                        for (Stock findingStock : findingStocks) {
                            if (findingStock.getUnitId() == findingImportOrderDetail.getUnitId() && findingStock.getProductId() == findingImportOrderDetail.getProductId()) {
                                checkNotEx = false;
                                Long currentQuan = findingStock.getQuantity();
                                findingStock.setQuantity(currentQuan + findingImportOrderDetail.getQuantity());
                                stockDAO.updateById(findingStock.getStockId() + "", findingStock);
                                break;
                            }
                        }
                        if (checkNotEx) {
                            Stock newStock = Stock.builder()
                                    .batchCode(submit_batch_code)
                                    .quantity(findingImportOrderDetail.getQuantity())
                                    .productId(findingImportOrderDetail.getProductId())
                                    .unitId(findingImportOrderDetail.getUnitId())
                                    .mfgDate(mfgDate)
                                    .expDate(expDate)
                                    .isDeleted(false)
                                    .build();
                            boolean checkAddStock = stockDAO.addNew(newStock);
                        }
                    }
                }

            }
            response.sendRedirect("UpdateImportOrder?update_success=true&imp_id=" + findingImportOrderDetail.getImportOrderId());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UpdateImportOrder?update_success=false&imp_id=" + imp_id);
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
