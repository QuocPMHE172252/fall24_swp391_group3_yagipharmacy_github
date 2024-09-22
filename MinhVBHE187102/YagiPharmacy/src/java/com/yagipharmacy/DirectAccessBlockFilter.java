/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/AdvancedFilter.java to edit this template
 */
package com.yagipharmacy;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class DirectAccessBlockFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();

        if (requestURI != null && (requestURI.endsWith(".jsp") || requestURI.endsWith(".html"))) {
            // Chặn truy cập trực tiếp, chuyển hướng đến Servlet khác hoặc trang lỗi
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/StuQuiz/ErrorPage"); // Hoặc chuyển hướng đến Servlet khác
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

}
