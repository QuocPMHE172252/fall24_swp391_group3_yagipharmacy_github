/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.yagipharmacy.controllers.manager;

import com.yagipharmacy.DAO.MajorCategoryDAO;
import com.yagipharmacy.DAO.StaffDAO;
import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.constant.services.AuthorizationService;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.MajorCategory;
import com.yagipharmacy.entities.Staff;
import com.yagipharmacy.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddStaff", urlPatterns = {"/manager/Add-UpdateStaff"})
public class AddStaff extends HttpServlet implements AuthorizationService {

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
            out.println("<title>Servlet AddStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddStaff at " + request.getContextPath() + "</h1>");
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
        List<Long> listAuth = Arrays.asList(2L);
        boolean checkAuth = acceptAuth(request, response, listAuth);
        if (!checkAuth) {
            response.sendRedirect("../Login");
            return;
        }
        UserDAO userDAO = new UserDAO();
        try {
            String userId = request.getParameter("u_id");
            if(userId!=null){
                User findingUser = userDAO.getById(userId);
                if(findingUser.getUserId()!=0){
                    StaffDAO staffDAO = new StaffDAO();
                    Staff findingStaff = staffDAO.getByUserId(userId);
                    request.setAttribute("findingUser", findingUser);
                    request.setAttribute("findingStaff", findingStaff);
                }
            }
            MajorCategoryDAO majorCategoryDAO = new MajorCategoryDAO();
            List<MajorCategory> majorCategorys = majorCategoryDAO.getAll();
            request.setAttribute("majorCategorys", majorCategorys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("AddStaff.jsp").forward(request, response);
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
        String user_phone_email = request.getParameter("user_phone_email");
        String staff_major = request.getParameter("staff_major");
        String staff_education = request.getParameter("staff_education");
        String staff_description = request.getParameter("staff_description");
        String staff_experience = request.getParameter("staff_experience");
        String staff_degree = request.getParameter("staff_degree");

        try {
            UserDAO userDAO = new UserDAO();
            User findingUser = userDAO.getByEmail(user_phone_email);
            if (findingUser.getUserId() == 0L) {
                findingUser = userDAO.getByPhone(user_phone_email);
            }
            if (findingUser.getUserId() == 0L) {
                request.setAttribute("error", "Email hoặc số điện thoại không tồn tại.");
                doGet(request, response);
                return;
            } else {
                StaffDAO staffDAO = new StaffDAO();
                Staff findingStaff = staffDAO.getByUserId(findingUser.getUserId() + "");
                if (findingStaff.getStaffId() != 0) {
                    findingStaff.setStaffMajorId(CalculatorService.parseLong(staff_major));
                    findingStaff.setStaffEducation(staff_education);
                    findingStaff.setStaffDescription(staff_description);
                    findingStaff.setStaffExperience(staff_experience);
                    findingStaff.setStaffDegree(staff_degree);
                    System.out.println(findingStaff.getStaffId());
                    boolean check = staffDAO.updateById(findingStaff.getStaffId()+"", findingStaff);
                    if (check) {
                        request.setAttribute("error", "Cập nhật nhân viên thành công.");
                        request.setAttribute("findingUser", findingUser);
                        request.setAttribute("findingStaff", findingStaff);
                        doGet(request, response);
                        return;
                    }
                } else {
                    Staff newStaff = Staff.builder()
                            .gender(true)
                            .isDeleted(false)
                            .staffMajorId(CalculatorService.parseLong(staff_major))
                            .staffEducation(staff_education)
                            .staffDescription(staff_description)
                            .staffExperience(staff_experience)
                            .userId(findingUser.getUserId())
                            .staffDegree(staff_degree)
                            .build();
                    boolean check = staffDAO.addNew(newStaff);
                    if (check) {
                        request.setAttribute("findingUser", findingUser);
                        request.setAttribute("findingStaff", newStaff);
                        request.setAttribute("error", "Thêm nhân viên mới thành công.");
                        doGet(request, response);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        doGet(request, response);
        return;

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
