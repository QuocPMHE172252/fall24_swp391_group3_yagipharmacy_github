/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.ProductCategoryDAO;
import com.yagipharmacy.DAO.ProductDAO;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductCategory;
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
@WebServlet(name = "CommonProducts", urlPatterns = {"/CommonProducts"})
public class CommonProducts extends HttpServlet {

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
            out.println("<title>Servlet CommonProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommonProducts at " + request.getContextPath() + "</h1>");
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
        ProductDAO productDAO = new ProductDAO();
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        Gson gson = new Gson();
        String size_of_page = request.getParameter("size_of_page");
        String see_more = request.getParameter("see_more");
        String type = request.getParameter("type");
        String target = request.getParameter("target");
        String sale_price = request.getParameter("sale_price");
        String kind = request.getParameter("drug_kind");
        String country = request.getParameter("country");
        String brand = request.getParameter("brand");
        String searching = request.getParameter("searching");
        String searchProduct = request.getParameter("searchProduct");
        request.setAttribute("type_value", type);
        request.setAttribute("target_value", target);
        request.setAttribute("sale_price_value", sale_price);
        request.setAttribute("drug_kind_value", kind);
        request.setAttribute("country_value", country);
        request.setAttribute("brand_value", brand);
        if (searching == null) {
            searching = "all";
        } else {
            if (!searching.equals("true")) {
                searching = "false";

            }
        }
        if (see_more == null) {
            see_more = "false";
        }
        if (searchProduct == null) {
            searchProduct = "all";
        } else {
            if (searchProduct.trim().equals("")) {
                searchProduct = "all";
            }
        }
        Long size_of_page_num = 12L;
        if (size_of_page != null) {
            size_of_page_num = CalculatorService.parseLong(size_of_page);
            request.setAttribute("size_of_page", size_of_page_num + 8);

        }
        if (see_more.equals("true")) {
            request.setAttribute("size_of_page", size_of_page_num + 8);
        } else {
            request.setAttribute("size_of_page", size_of_page_num);
        }

        try {
            List<ProductCategory> pCates = productCategoryDAO.getListChildren();
            request.setAttribute("pCates", pCates);

            List<String> targets = productDAO.getAllProductTarget();
            request.setAttribute("targets", targets);

            List<String> countries = productDAO.getAllProductCountry();
            request.setAttribute("countries", countries);

            List<String> brands = productDAO.getAllProductBrand();
            request.setAttribute("brands", brands);
            
            List<Product> products = productDAO.getAll();
            if (searching.equals("all")) {
            }
            if(searching.equals("true")){
                products = filterByName(products, searchProduct);
                String jsonProducts = gson.toJson(products);
                request.setAttribute("jsonProducts", jsonProducts);
                request.getRequestDispatcher("CommonProducts.jsp").forward(request, response);
                return;
            }
            products = filterByCate(products, type);
            products = filterByTarget(products, target);
            products = filterBySellPrice(products, sale_price);
            products = filterByKind(products, kind);
            products = filterByCountry(products, country);
            products = filterByBrand(products, brand);
            products = filterBySize(products, size_of_page_num);
            String jsonProducts = gson.toJson(products);
            request.setAttribute("jsonProducts", jsonProducts);

            
            request.getRequestDispatcher("CommonProducts.jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    public List<Product> filterByCate(List<Product> products, String cateId) {
        List<Product> resultList = new ArrayList<>();
        if (cateId == null) {
            cateId = "all";
        }
        if (cateId.equals("all")) {
            return products;
        }
        Long cateId_num = CalculatorService.parseLong(cateId);
        if (cateId_num == 0L) {
            return products;
        }
        for (Product product : products) {
            if (product.getProductCategory().getProductCategoryId() == cateId_num) {
                resultList.add(product);
            }
        }
        return resultList;
    }

    public List<Product> filterByTarget(List<Product> products, String target) {
        List<Product> resultList = new ArrayList<>();
        if (target == null) {
            target = "all";
        }
        if (target.equals("all")) {
            return products;
        }
        for (Product product : products) {
            if (product.getProductTarget().contains(target)) {
                resultList.add(product);
            }
        }
        return resultList;
    }

    public List<Product> filterBySellPrice(List<Product> products, String sellPrice) {
        List<Product> resultList = new ArrayList<>();
        if (sellPrice == null) {
            sellPrice = "all";
        }
        if (sellPrice.equals("all")) {
            return products;
        }
        Long a = 0L;
        Long b = Long.MAX_VALUE;

        if (sellPrice.equals("0")) {
            b = 100000L;
        }
        if (sellPrice.equals("1")) {
            a = 100000L;
            b = 3 * a;
        }
        if (sellPrice.equals("2")) {
            a = 300000L;
            b = 500000L;
        }
        if (sellPrice.equals("3")) {
            a = 500000L;
        }

        for (Product product : products) {
            if (!product.getProductUnits().isEmpty()) {
                int size = product.getProductUnits().size();
                Double sPrice = product.getProductUnits().get(size - 1).getSellPrice();
                if (sPrice >= a && sPrice <= b) {
                    resultList.add(product);
                }
            }
        }
        return resultList;
    }

    public List<Product> filterByKind(List<Product> products, String kind) {
        List<Product> resultList = new ArrayList<>();
        boolean fil = true;
        if (kind == null) {
            kind = "all";
        }
        if (kind.equals("true")) {
            fil = true;
        } else if (kind.equals("false")) {
            fil = false;
        } else {
            kind = "all";
        }
        if (kind.equals("all")) {
            return products;
        }
        for (Product product : products) {
            if (product.isPrescription() == fil) {
                resultList.add(product);
            }
        }

        return resultList;
    }

    public List<Product> filterByCountry(List<Product> products, String country) {
        List<Product> resultList = new ArrayList<>();
        if (country == null) {
            country = "all";
        }
        if (country.equals("all")) {
            return products;
        }
        for (Product product : products) {
            if (product.getProductCountryCode().equals(country)) {
                resultList.add(product);
            }
        }
        return resultList;
    }

    public List<Product> filterByBrand(List<Product> products, String brand) {
        List<Product> resultList = new ArrayList<>();
        if (brand == null) {
            brand = "all";
        }
        if (brand.equals("all")) {
            return products;
        }
        for (Product product : products) {
            if (product.getBrand().equals(brand)) {
                resultList.add(product);
            }
        }
        return resultList;
    }

    public List<Product> filterBySize(List<Product> products, Long sizeOfPage) {
        int sizeOfAll = products.size();
        List<Product> resultList = new ArrayList<>();
        for (int i = 0; i < sizeOfPage; i++) {
            if (i <= sizeOfAll - 1) {
                resultList.add(products.get(i));
            } else {
                break;
            }
        }
        return resultList;
    }

    public List<Product> filterByName(List<Product> products, String name) {
        List<Product> resultList = new ArrayList<>();
        if (name == null) {
            name = "all";
        }
        if (name.equals("all")) {
            return products;
        }
        for (Product product : products) {
            if (product.getProductName().contains(name)) {
                resultList.add(product);
            }
        }
        return resultList;
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
