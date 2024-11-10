/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.ImportOrderDAO;
import com.yagipharmacy.DAO.ImportOrderDetailDAO;
import com.yagipharmacy.DAO.ProductDAO;
import com.yagipharmacy.DAO.SupplierDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.entities.ImportOrder;
import com.yagipharmacy.entities.ImportOrderDetail;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.Supplier;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreateImportOrder", urlPatterns = {"/manager/CreateImportOrder"})
public class CreateImportOrder extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet CreateImportOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateImportOrder at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDAO = new ProductDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        List<Product> products = new ArrayList<>();
        List<Supplier> suppliers = new ArrayList<>();
        try {
            products = productDAO.getAllNoLongDes();
            suppliers = supplierDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String productsJson = new Gson().toJson(products);
        String suppliersJson = new Gson().toJson(suppliers);
        request.setAttribute("productsJson", productsJson);
        request.setAttribute("suppliersJson", suppliersJson);
        request.getRequestDispatcher("CreateImportOrder.jsp").forward(request, response);
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
        ImportOrderDAO importOrderDAO = new ImportOrderDAO();
        ImportOrderDetailDAO importOrderDetailDAO = new ImportOrderDetailDAO();
        String product_list_str = request.getParameter("product_list_str");
        String[] arrProductStr = product_list_str.split(",");
        int arrLe = arrProductStr.length;
        String expected_date = request.getParameter("expected_date");
        String import_order_code = request.getParameter("import_order_code");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long newImportOrderId = -1L;
        try {
            Date importExpectedDate = formatter.parse(expected_date);
            ImportOrder newImportOrder = ImportOrder.builder()
                    .importOrderCode(import_order_code)
                    .createdBy(1L)
                    .createdDate(new Date())
                    .approvedBy(null)
                    .approvedDate(new Date(0))
                    .importExpectedDate(importExpectedDate)
                    .isAccepted(null)
                    .rejectedReason("")
                    .isDeleted(false)
                    .build();
            boolean checkExistOrder = false;
            ImportOrder findingImportOrder = importOrderDAO.getByOrderCode(import_order_code);
            if (findingImportOrder.getImportOrderId() != 0L) {
                checkExistOrder = true;
            }
            if (!checkExistOrder) {
                newImportOrderId = importOrderDAO.addNewAndGetKey(newImportOrder);
                System.out.println(newImportOrderId);
            }
            if (newImportOrderId != -1L) {
                List<Long> supplierList = new ArrayList();
                for (int i = 0; i < arrLe; i++) {
                    String unitIdStr = request.getParameter("unit_for_product_" + arrProductStr[i]);
                    String quantityStr = request.getParameter("quantity_" + arrProductStr[i]);
                    String supplier_id = request.getParameter("supplier_id_" + arrProductStr[i]);

                    Long productId = CalculatorService.parseLong(arrProductStr[i]);
                    Long unitId = CalculatorService.parseLong(unitIdStr);
                    Long quantity = CalculatorService.parseLong(quantityStr);
                    Long supplierId = CalculatorService.parseLong(supplier_id);
                    System.out.println(supplier_id);
                    boolean checkNotEx = true;
                    for (Long supId : supplierList) {
                        if(supplierId.equals(supId)){
                            checkNotEx = false;
                            break;
                        }
                    }
                    if(checkNotEx){
                        supplierList.add(supplierId);
                    }
                    ImportOrderDetail newImportOrderDetail = ImportOrderDetail.builder()
                            .batchCode(null)
                            .importOrderId(newImportOrderId)
                            .productId(productId)
                            .unitId(unitId)
                            .quantity(quantity)
                            .importPrice(null)
                            .importDate(null)
                            .supplierId(supplierId)
                            .processing(0L)
                            .isDeleted(false)
                            .build();
                    boolean check = importOrderDetailDAO.addNew(newImportOrderDetail);
                    
                }
                for (Long supId : supplierList) {
                    
                }
                response.sendRedirect("ImportOrderList?created=true");
            }else{
                request.setAttribute("error", "Mã nhập hàng đã tồn tại");
                doGet(request, response);
            }

        } catch (Exception e) {            
            e.printStackTrace();
            request.setAttribute("error", "Mã nhập hàng đã tồn tại");
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
