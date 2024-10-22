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
@WebServlet(name = "EditProduct", urlPatterns = { "/manager/EditProduct" })
public class EditProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            productCategorys = productCategoryDAO.getListLastChildren();
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
                units_string += pu.getUnitId() + "_" + pu.getQuantityPerUnit() + "_" + pu.isCanBeSold() + "_"
                        + pu.getSellPrice() + ",";
            }
            excipients_string = excipients_string.substring(0, excipients_string.length() - 1);
            units_string = units_string.substring(0, units_string.length() - 1);
            request.setAttribute("unitsJson", unitsJson);
            request.setAttribute("suppliers", suppliers);
            request.setAttribute("excipientsJson", excipientsJson);
            request.setAttribute("productCategorys", productCategorys);
            request.setAttribute("excipients_string", excipients_string);
            request.setAttribute("units_string", units_string);
            request.setAttribute("product", product);
            request.setAttribute("product_excipients", productExcipients);
            request.setAttribute("product_units", productUnits);
            request.setAttribute("product_images", productImages);
            request.setAttribute("is_prescription", product.getIsPrescription());
            request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long product_id = CalculatorService.parseLong(request.getParameter("product_id"));
        String product_code = request.getParameter("product_code");
        String product_name = request.getParameter("product_name");
        String product_category = request.getParameter("product_category");
        String dosage_form = request.getParameter("dosage_form");
        String product_specification = request.getParameter("product_specification");
        String excipients_string = request.getParameter("excipients_string");
        String suplier_id = request.getParameter("suplier_id");
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
            Product existingProduct = productDAO.getById(product_id + "");
            if (existingProduct.getProductId() != 0) {
                boolean isPrescription = request.getParameter("is_prescription") != null
                        && request.getParameter("is_prescription").equals("1");
                Product updatedProduct = Product.builder()
                        .productId(product_id)
                        .productCode(product_code)
                        .productCategoryId(CalculatorService.parseLong(product_category))
                        .productCountryCode(product_country_code)
                        .supplierId(CalculatorService.parseLong(suplier_id))
                        .productTarget(product_target)
                        .productName(product_name)
                        .dosageForm(dosage_form)
                        .productSpecification(product_specification)
                        .productDescription(product_desciption)
                        .createdDate(existingProduct.getCreatedDate())
                        .isDeleted(existingProduct.isDeleted())
                        .isPrescription(isPrescription)
                        .build();
                boolean updateProductSuccess = productDAO.updateById(product_id + "", updatedProduct);
                if (updateProductSuccess) {
                    String[] arrExcipients = excipients_string.split(",");
                    String[] arrUnits = units_string.split(",");

                    int eLength = arrExcipients.length;
                    int uLength = arrUnits.length;

                    // Delete existing product excipients
                    List<ProductExcipient> existingProductExcipients = productExcipientDAO
                            .getListByProductId(product_id + "");
                    for (ProductExcipient pe : existingProductExcipients) {
                        productExcipientDAO.deleteById(pe.getProductExcipientId() + "");
                    }

                    // Delete existing product units
                    List<ProductUnit> existingProductUnits = productUnitDAO.getListByProductId(product_id + "");
                    for (ProductUnit pu : existingProductUnits) {
                        productUnitDAO.deleteById(pu.getProductUnitId() + "");
                    }

                    // Add new product excipients
                    for (int i = 0; i < eLength; i++) {
                        String[] arrAttr = arrExcipients[i].split("_");
                        ProductExcipient newPE = ProductExcipient.builder()
                                .productId(product_id)
                                .excipientId(CalculatorService.parseLong(arrAttr[0]))
                                .quantity(CalculatorService.parseDouble(arrAttr[1]))
                                .unitMeasurement(arrAttr[2])
                                .build();
                        productExcipientDAO.addNew(newPE);
                    }

                    // Add new product units
                    Long parentIdCount = 0L;
                    for (int i = 0; i < uLength; i++) {
                        String[] arrAttr = arrUnits[i].split("_");
                        ProductUnit newPU = ProductUnit.builder()
                                .parentId(parentIdCount > 0 ? parentIdCount : null)
                                .productId(product_id)
                                .unitId(CalculatorService.parseLong(arrAttr[0]))
                                .sellPrice(CalculatorService.parseDouble(arrAttr[3]))
                                .quantityPerUnit(CalculatorService.parseLong(arrAttr[1]))
                                .productUnitName("")
                                .canBeSold(arrAttr[2].equals("true"))
                                .isDeleted(false)
                                .build();
                        parentIdCount = productUnitDAO.addNewAndGetKey(newPU);
                    }

                    // Update product image
                    ProductImage existingProductImage = productImageDAO.getListByProductId(product_id + "").get(0);
                    existingProductImage.setImageBase64(product_image_submit);
                    productImageDAO.updateById(existingProductImage.getProductImageId() + "", existingProductImage);

                    request.setAttribute("success", true);
                    doGet(request, response);
                } else {
                    request.setAttribute("error", "Cập nhật sản phẩm thất bại.");
                    request.setAttribute("success", false);
                    doGet(request, response);
                }
            } else {
                request.setAttribute("error", "Sản phẩm không tồn tại.");
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
