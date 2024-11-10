/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yagipharmacy.DAO.ProductDAO;
import com.yagipharmacy.DAO.SaleOrderDAO;
import com.yagipharmacy.DAO.SaleOrderDetailDAO;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.constant.services.MailService;
import com.yagipharmacy.entities.CartDetail;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.SaleOrder;
import com.yagipharmacy.entities.SaleOrderDetail;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreateSaleOrder", urlPatterns = {"/CreateSaleOrder"})
public class CreateSaleOrder extends HttpServlet {

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
            out.println("<title>Servlet CreateSaleOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateSaleOrder at " + request.getContextPath() + "</h1>");
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
        String decodedString = URLDecoder.decode(getCookieValue(request, "cart"), "UTF-8");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> stringList = gson.fromJson(decodedString, listType);
        String[] stringArray = stringList.toArray(new String[0]);
        int le = stringArray.length;
        ProductDAO productDAO = new ProductDAO();
        List<CartDetail> cartDetails = new ArrayList<>();
        try {
            for (int i = 0; i < le; i++) {
                String[] cartDetailArr = stringArray[i].split("_");
                Product findingProduct = productDAO.getById(cartDetailArr[0]);
                CartDetail newCartDetail = CartDetail.builder()
                        .product(findingProduct)
                        .selectedUnit(CalculatorService.parseLong(cartDetailArr[1]))
                        .quantity(CalculatorService.parseLong(cartDetailArr[2]))
                        .build();
                cartDetails.add(newCartDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String cartDetailsJson = gson.toJson(cartDetails);
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        if (userAuth != null) {
            
        }
        request.setAttribute("cartDetailsJson", cartDetailsJson);
        request.getRequestDispatcher("CreateSaleOrder.jsp").forward(request, response);
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
        User userAuth = (User) request.getSession().getAttribute("userAuth");
        Long uId = null;
        if (userAuth == null) {
            uId = null;
        } else {
            uId = userAuth.getUserId();
        }
        String decodedString = URLDecoder.decode(getCookieValue(request, "cart"), "UTF-8");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> stringList = gson.fromJson(decodedString, listType);
        String[] stringArray = stringList.toArray(new String[0]);
        int le = stringArray.length;
        SaleOrderDAO saleOrderDAO = new SaleOrderDAO();
        SaleOrderDetailDAO saleOrderDetailDAO = new SaleOrderDetailDAO();
        try {
            SaleOrder newSaleOrder = SaleOrder.builder()
                    .orderBy(uId)
                    .receiverName(request.getParameter("name"))
                    .receiverPhone(request.getParameter("phone"))
                    .specificAddress(request.getParameter("location"))
                    .receiverEmail(request.getParameter("email"))
                    .totalPrice(CalculatorService.parseDouble(request.getParameter("total_submit").replace(",", "")))
                    .createdDate(new Date())
                    .isPaid(false)
                    .isDeleted(false)
                    .processing(0L)
                    .build();
            Long genKey = saleOrderDAO.addNewAndGetKey(newSaleOrder);
            for (int i = 0; i < le; i++) {
                String[] cartDetailArr = stringArray[i].split("_");
                SaleOrderDetail newSaleOrderDetail = SaleOrderDetail.builder()
                        .saleOrderId(genKey)
                        .productId(CalculatorService.parseLong(cartDetailArr[0]))
                        .unitId(CalculatorService.parseLong(cartDetailArr[1]))
                        .quantity(CalculatorService.parseLong(cartDetailArr[2]))
                        .batchCode("HEQ")
                        .isDeleted(false)
                        .build();
                saleOrderDetailDAO.addNew(newSaleOrderDetail);
            }
            SaleOrder findingOrder = saleOrderDAO.getById(genKey + "");
            
            MailService.sentEmail(request.getParameter("email"), "Xác nhận đơn hàng thành công", MailService.createSaleOrderSuccessfullMail(findingOrder));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ViewCart?buy_success=1");
    }
    
    public String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
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
