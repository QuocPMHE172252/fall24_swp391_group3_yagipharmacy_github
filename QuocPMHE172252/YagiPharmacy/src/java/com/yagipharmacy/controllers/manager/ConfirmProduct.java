/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.ExcipientDAO;
import com.yagipharmacy.DAO.ProductCategoryDAO;
import com.yagipharmacy.DAO.ProductDAO;
import com.yagipharmacy.DAO.ProductExcipientDAO;
import com.yagipharmacy.DAO.ProductImageDAO;
import com.yagipharmacy.DAO.ProductUnitDAO;
import com.yagipharmacy.DAO.SupplierDAO;
import com.yagipharmacy.DAO.UnitDAO;
import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.entities.Excipient;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductCategory;
import com.yagipharmacy.entities.ProductExcipient;
import com.yagipharmacy.entities.ProductImage;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.entities.Supplier;
import com.yagipharmacy.entities.Unit;
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
import java.util.List;

/**
 *
 * @author author
 */
@WebServlet(name = "ConfirmProduct", urlPatterns = {"/manager/ConfirmProduct"})
public class ConfirmProduct extends HttpServlet implements AuthorizationService{

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
            out.println("<title>Servlet ConfirmProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmProduct at " + request.getContextPath() + "</h1>");
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
        UnitDAO unitDAO = new UnitDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        ExcipientDAO excipientDAO = new ExcipientDAO();
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        ProductExcipientDAO productExcipientDAO = new ProductExcipientDAO();
        ProductUnitDAO productUnitDAO = new ProductUnitDAO();
        ProductImageDAO productImageDAO = new ProductImageDAO();
        List<Unit> units = new ArrayList<>();
        List<Supplier> suppliers = new ArrayList<>();
        List<Excipient> excipients = new ArrayList<>();
        List<ProductCategory> productCategorys = new ArrayList<>();
        try {
            Gson gson = new Gson();
            units = unitDAO.getAll();
            String unitsJson = gson.toJson(units);
            suppliers = supplierDAO.getAll();
            excipients = excipientDAO.getAll();
            String excipientsJson = gson.toJson(excipients);
            productCategorys = productCategoryDAO.getListChildren();
            String productId = request.getParameter("product_id");
            Product product = productDAO.getById(productId);
            List<ProductExcipient> productExcipients = productExcipientDAO.getListByProductId(productId);
            List<ProductUnit> productUnits = productUnitDAO.getListByProductId(productId);
            List<ProductImage> productImages = productImageDAO.getListByProductId(productId);
            String excipients_string = "";
            String units_string = "";
            for (ProductExcipient pe : productExcipients) {
                excipients_string += pe.getExcipientId() + "_" + pe.getQuantity() + "_" + pe.getUnitMeasurement() + ",";
            }
            for (ProductUnit pu : productUnits) {
                units_string += pu.getUnitId() + "_" + pu.getQuantityPerUnit() + "_" + pu.isCanBeSold() + "_" + pu.getSellPrice() + ",";
            }
            if(excipients_string.length()>0){
                excipients_string = excipients_string.substring(0, excipients_string.length() - 1);
            }
            if(units_string.length()>0){
                units_string = units_string.substring(0, units_string.length() - 1);
            }
            
            request.setAttribute("unitsJson", unitsJson);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("excipientsJson", excipientsJson);
            request.setAttribute("productCategorys", productCategorys);
            //
            //Product product = productDAO.getById(productId);
            //boolean isPrescription = product.getIsPrescription();
            String productCountryCode = product.getProductCountryCode();
            request.setAttribute("productCountryCode", productCountryCode);
            request.setAttribute("product", product);
            request.setAttribute("product_excipients", productExcipients);
            request.setAttribute("product_units", productUnits);
            request.setAttribute("product_images", productImages);
            request.setAttribute("excipients_string", excipients_string);
            request.setAttribute("units_string", units_string);
            request.setAttribute("success", false);
            request.getRequestDispatcher("ConfirmProduct.jsp").forward(request, response);
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
        try {

            String rejectReason = request.getParameter("rejectReason");
            String productId = request.getParameter("product_id");
            String getAuthorId = request.getParameter("getAuthorId");
            String status = !request.getParameter("status").equals("Appreoved")?"1":"0";
            ProductDAO productDAO = new ProductDAO();

            Product product = productDAO.getById(productId);
            UserDAO udao = new UserDAO();
            User authorNew = udao.getById(getAuthorId);
            String host = request.getServerName();
            int port = request.getServerPort();
             User curentUser = (User) request.getSession().getAttribute("userAuth");
            String subject = curentUser.getUserName() + " have been"+ (status.equals("0")?"aprroved":"rejected") +" your new product.";
            String body = "Reason: " + rejectReason;
            if(status.equals("0")) body = "Thanks";
            boolean check = productDAO.updateStatusById(productId, status);
            new MailService().sentEmail(authorNew.getUserEmail(), subject, body);
            
            response.sendRedirect("/YagiPharmacy/admin/ProductsList");  
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
