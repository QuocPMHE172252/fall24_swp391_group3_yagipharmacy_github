/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.admin;

import com.google.gson.Gson;
import com.yagipharmacy.DAO.ProductDAO;
import com.yagipharmacy.DAO.SaleOrderDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductRevenueQuanDTO;
import com.yagipharmacy.entities.RevenueDTO;
import com.yagipharmacy.entities.SaleOrder;
import com.yagipharmacy.entities.SaleOrderDetail;
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
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author author
 */
@WebServlet(name = "AdminDashboard", urlPatterns = {"/admin/AdminDashboard"})
public class AdminDashboard extends HttpServlet implements AuthorizationService {

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
            out.println("<title>Servlet AdminDashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboard at " + request.getContextPath() + "</h1>");
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
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        if (userAuth == null) {
            response.sendRedirect("../Login");
            return;
        }
        List<Long> roleList = Arrays.asList(1L, 2L, 3L);
        boolean checkAcpt = acceptAuth(request, response, roleList);
        if (!checkAcpt) {
            response.sendRedirect("../ErrorPage");
            return;
        }

        String year = request.getParameter("year");
        if (year == null) {
            year = new Date().getYear()+1900 + "";
        }
        request.setAttribute("year", CalculatorService.parseLong(year));
        String product_id = request.getParameter("product_id");
        if (product_id == null) {
            product_id = "2";
        }
        request.setAttribute("product_id", CalculatorService.parseLong(product_id));
        try {
            SaleOrderDAO saleOrderDAO = new SaleOrderDAO();
            ProductDAO productDAO = new ProductDAO();
            List<SaleOrder> listAll = saleOrderDAO.getAll();
            listAll = filterByProduct(product_id, listAll);
            listAll = filterByYear(year, listAll);
            RevenueDTO newRevenueDTO = getRevenueDTO(listAll, product_id);
            request.setAttribute("reDTO", newRevenueDTO);
            List<Product> listP = productDAO.getAll();
            request.setAttribute("listP", listP);
            List<ProductRevenueQuanDTO> litPRQs = productDAO.getTop10PRQ();
            String litPRQsJson = new Gson().toJson(litPRQs);
            request.setAttribute("litPRQsJson", litPRQsJson);
            Double dailyTotal = saleOrderDAO.getDailyRevenue();
            Double allTotal = saleOrderDAO.getAllRevenue();
            request.setAttribute("dailyTotal", CalculatorService.formatPrice(dailyTotal));
            request.setAttribute("allTotal", CalculatorService.formatPrice(allTotal));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public List<SaleOrder> filterByYear(String yearString, List<SaleOrder> inputList) {
        Long year = CalculatorService.parseLong(yearString);
        List<SaleOrder> result = new ArrayList<>();
        for (SaleOrder saleOrder : inputList) {
            if (saleOrder.getCreatedDate().getYear()+1900 == year) {
                result.add(saleOrder);
            }
        }
        return result;
    }

    public List<SaleOrder> filterByProduct(String productId, List<SaleOrder> inputList) {
        List<SaleOrder> result = new ArrayList<>();
        for (SaleOrder saleOrder : inputList) {
            for (SaleOrderDetail saleOrderDetail : saleOrder.getSaleOrderDetails()) {
                if (productId.equals(saleOrderDetail.getProductId() + "")) {
                    result.add(saleOrder);
                }
            }
        }
        return result;
    }

    public RevenueDTO getRevenueDTO(List<SaleOrder> inputList, String productId) {
        Double m1 = 0.0;
        Double m2 = 0.0;
        Double m3 = 0.0;
        Double m4 = 0.0;
        Double m5 = 0.0;
        Double m6 = 0.0;
        Double m7 = 0.0;
        Double m8 = 0.0;
        Double m9 = 0.0;
        Double m10 = 0.0;
        Double m11 = 0.0;
        Double m12 = 0.0;
        for (SaleOrder saleOrder : inputList) {
            for (SaleOrderDetail saleOrderDetail : saleOrder.getSaleOrderDetails()) {
                if (productId.equals(saleOrderDetail.getProductId() + "")) {
                    if (saleOrder.getCreatedDate().getMonth() == 0) {
                        m1 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 1) {
                        m2 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 2) {
                        m3 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 3) {
                        m4 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 4) {
                        m5 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 5) {
                        m6 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 6) {
                        m7 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 7) {
                        m8 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 8) {
                        m9 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 9) {
                        m10 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 10) {
                        m11 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }
                    if (saleOrder.getCreatedDate().getMonth() == 11) {
                        m12 += saleOrderDetail.getQuantity()*saleOrderDetail.getProductUnit().getSellPrice();
                    }

                }
            }

        }
        try {
            ProductDAO productDAO = new ProductDAO();
            Product findingProduct = productDAO.getById(productId);
            RevenueDTO newRevenueDTO = RevenueDTO.builder()
                    .m1(m1)
                    .m2(m2)
                    .m3(m3)
                    .m4(m4)
                    .m5(m5)
                    .m6(m6)
                    .m7(m7)
                    .m8(m8)
                    .m9(m9)
                    .m10(m10)
                    .m11(m11)
                    .m12(m12)
                    .product(findingProduct)
                    .build();
            return newRevenueDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RevenueDTO.builder().build();
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
