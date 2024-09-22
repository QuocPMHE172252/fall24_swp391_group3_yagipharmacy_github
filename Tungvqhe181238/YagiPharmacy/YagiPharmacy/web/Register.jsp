<%-- 
    Document   : Login
    Created on : Sep 21, 2024, 1:59:08 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Website - www.codingnepalweb.com -->
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Form Validation in HTML | CodingNepal</title>
        <link rel="stylesheet" href="style.css">
        <!-- Fontawesome CDN Link For Icons -->
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap');

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Open Sans', sans-serif;
            }

            body {
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                padding: 0 10px;
                background: rgba(59, 130, 246, .5);
            }

            form {
                padding: 25px;
                background: #fff;
                max-width: 500px;
                width: 100%;
                border-radius: 7px;
                box-shadow: 0 10px 15px rgba(0, 0, 0, 0.05);
            }

            form h2 {
                font-size: 27px;
                text-align: center;
                margin: 0px 0 30px;
            }

            form .form-group {
                margin-bottom: 15px;
                position: relative;
            }

            form label {
                display: block;
                font-size: 15px;
                margin-bottom: 7px;
            }

            form input,
            form select {
                height: 45px;
                padding: 10px;
                width: 100%;
                font-size: 15px;
                outline: none;
                background: #fff;
                border-radius: 3px;
                border: 1px solid #bfbfbf;
            }

            form input:focus,
            form select:focus {
                border-color: #9a9a9a;
            }

            form input.error,
            form select.error {
                border-color: #f91919;
                background: #f9f0f1;
            }

            form small {
                font-size: 14px;
                margin-top: 5px;
                display: block;
                color: #f91919;
            }

            form .password i {
                position: absolute;
                right: 0px;
                height: 45px;
                top: 28px;
                font-size: 13px;
                line-height: 45px;
                width: 45px;
                cursor: pointer;
                color: #939393;
                text-align: center;
            }

            .submit-btn {
                margin-top: 30px;
            }

            .submit-btn input {
                color: white;
                border: none;
                height: auto;
                font-size: 16px;
                padding: 13px 0;
                border-radius: 5px;
                cursor: pointer;
                font-weight: 500;
                text-align: center;
                background: rgba(59, 130, 246, .5);
                transition: 0.2s ease;
            }

            .submit-btn input:hover {
                background: rgba(2, 98, 253, 0.5);
            }
            .error{
                color: red;
                font-size: 12px;
            }
        </style>
    </head>

    <body>

        <form action="Register" method="post" id="register_form">
            <img src="https://cms-prod.s3-sgn09.fptcloud.com/smalls/Logo_LC_Default_2e36f42b6b.svg" alt="">
            <h2>Đăng kí</h2>
            <div class="form-group fullname">
                <label for="fullname">Họ và Tên</label>
                <input type="text" id="fullname" name="fullname" placeholder="Enter your full name" value="${fullname}">
            </div>
            <div class="form-group phone">
                <label for="phone">Số điện thoại</label>
                <input type="text" id="phone" name="phone" placeholder="Enter your phone" value="${phone}">
                <br><span class="error" id="phone_error">${phoneError}</span>
            </div>
            <div class="form-group email">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" placeholder="Enter your email" value="${email}">
                <br><span class="error" id="email_error">${emailError}</span>
            </div>
            <div class="form-group password">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" value="${password}">
                <i id="pass-toggle-btn" class="fa-solid fa-eye"></i>
                <br><span class="error" id="password_error"></span>
            </div>
            <div class="form-group submit-btn">
                <input type="button" value="Đăng kí" onclick="validateSubmit()">
            </div>
            <br><span class="error" id="email_error">${systemError}</span>
            <div class="form-group submit-btn">
                <a href="Login">Bạn đã có tải khoản?</a>
            </div>  
        </form>
    </body>
    <script>
        function validateSubmit() {
            var fullname = document.getElementById("fullname").value;
            var phone = document.getElementById("phone").value;
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;
            document.getElementById("phone_error").innerHTML = '';
            document.getElementById("email_error").innerHTML = '';
            document.getElementById("password_error").innerHTML = '';
            var check = true;
            if (!isValidPhoneNumber(phone)) {
                document.getElementById("phone_error").innerHTML = 'Số điện thoại không hợp lệ!';
                check = false;
            }
            if (!isValidEmail(email)) {
                document.getElementById("email_error").innerHTML = 'Email không hợp lệ!';
                check = false;
            }
            if (!isValidPassword(password)) {
                document.getElementById("password_error").innerHTML = 'Mật khẩu phải chứa từ 8-16 kí tự gồm chữ in hoa chữ thường và số!';
                check = false;
            }
            if (check === true) {
                document.getElementById("register_form").submit();
            }
        }

        function isValidPhoneNumber(phoneNumber) {
            const cleanedNumber = phoneNumber.replace(/\s+/g, '').replace(/-/g, '');
            if (!/^(\+|\d)/.test(cleanedNumber)) {
                return false;
            }
            if (cleanedNumber.length < 10 || cleanedNumber.length > 15) {
                return false;
            }
            return true;
        }
        function isValidPassword(password) {
            if (password.length < 8 || password.length > 16) {
                return false;
            }
            if (!/[A-Z]/.test(password)) {
                return false;
            }
            if (!/[a-z]/.test(password)) {
                return false;
            }
            if (!/\d/.test(password)) {
                return false;
            }
            return true;
        }
        function isValidEmail(email) {
            const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            return emailRegex.test(email);
        }
    </script>
</html>
