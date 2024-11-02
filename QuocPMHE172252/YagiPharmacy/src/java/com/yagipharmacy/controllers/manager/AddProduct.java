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
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Excipient;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductCategory;
import com.yagipharmacy.entities.ProductExcipient;
import com.yagipharmacy.entities.ProductImage;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.entities.Supplier;
import com.yagipharmacy.entities.Unit;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/manager/AddProduct"})
public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        UnitDAO unitDAO = new UnitDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        ExcipientDAO excipientDAO = new ExcipientDAO();
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
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
            productCategorys = productCategoryDAO.getListLastChildren();
            request.setAttribute("unitsJson", unitsJson);
            request.setAttribute("excipientsJson", excipientsJson);
            request.setAttribute("productCategorys", productCategorys);
            request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
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
        String product_code = request.getParameter("product_code");
        String product_name = request.getParameter("product_name");
        String product_category = request.getParameter("product_category");
        String dosage_form = request.getParameter("dosage_form");
        String product_specification = request.getParameter("product_specification");
        String excipients_string = request.getParameter("excipients_string");
        String brand = request.getParameter("brand");
        String product_country_code = request.getParameter("product_country_code");
        String is_prescription = request.getParameter("is_prescription");
        String units_string = request.getParameter("units_string");
        String product_image_submit = request.getParameter("product_image_submit");
        String product_target = request.getParameter("product_target");
        String product_desciption = request.getParameter("product_desciption");

        ProductDAO productDAO = new ProductDAO();
        ProductUnitDAO productUnitDAO = new ProductUnitDAO();
        ProductExcipientDAO productExcipientDAO = new ProductExcipientDAO();
        ProductImageDAO productImageDAO = new ProductImageDAO();
        try {
            Product findingProduct = productDAO.getByProductCode(product_code);
            if (findingProduct.getProductId() == 0) {
                Product newProduct = Product.builder()
                        .productId(0L)
                        .productCode(product_code)
                        .authorId(1L)
                        .productCategoryId(CalculatorService.parseLong(product_category))
                        .productCountryCode(product_country_code)
                        .brand(brand)
                        .productTarget(product_target)
                        .productName(product_name)
                        .dosageForm(dosage_form)
                        .productSpecification(product_specification)
                        .productDescription(product_desciption)
                        .createdDate(new Date())
                        .isPrescription(is_prescription.equals("1"))
                        .isDeleted(false)
                        .build();
                Long genId = productDAO.addNewAndGetKey(newProduct);
                ProductImage newProductImg = ProductImage.builder()
                        .productId(genId)
                        .imageBase64(product_image_submit)
                        .isMain(true)
                        .isDeleted(false)
                        .build();
                productImageDAO.addNew(newProductImg);
                if (genId != -1L) {
                    String[] arrExcipients = excipients_string.split(",");
                    String[] arrUnits = units_string.split(",");

                    int eLength = arrExcipients.length;
                    int uLength = arrUnits.length;
                    for (int i = 0; i < eLength; i++) {
                        String [] arrAttr = arrExcipients[i].split("_");
                        ProductExcipient newPE = ProductExcipient.builder()
                                .productId(genId)
                                .excipientId(CalculatorService.parseLong(arrAttr[0]))
                                .quantity(CalculatorService.parseDouble(arrAttr[1]))
                                .unitMeasurement(arrAttr[2])
                                .build();
                        productExcipientDAO.addNew(newPE);
                    }
                    Long parentIdCount = 0L;
                    for (int i = 0; i < uLength; i++){
                        String [] arrAttr = arrUnits[i].split("_");
                        ProductUnit newPU = ProductUnit.builder()
                                .parentId(parentIdCount>0?parentIdCount:null)
                                .productId(genId)
                                .unitId(CalculatorService.parseLong(arrAttr[0]))
                                .sellPrice(CalculatorService.parseDouble(arrAttr[3]))
                                .quantityPerUnit(CalculatorService.parseLong(arrAttr[1]))
                                .productUnitName("")
                                .canBeSold(arrAttr[2].equals("true"))
                                .isDeleted(false)
                                .build();
                        parentIdCount = productUnitDAO.addNewAndGetKey(newPU);
                    }
                }
                request.setAttribute("success", true);
                doGet(request, response);
            } else {
                request.setAttribute("error", "Mã sản phẩm đã tồn tại.");
                doGet(request, response);
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
