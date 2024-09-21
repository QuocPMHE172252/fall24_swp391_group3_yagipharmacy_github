<%-- 
    Document   : Verify
    Created on : Sep 21, 2024, 9:13:05 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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
        <form action="Verify" method="post">
            <img src="https://cms-prod.s3-sgn09.fptcloud.com/smalls/Logo_LC_Default_2e36f42b6b.svg" alt="">
            <h2>Kích hoạt tài khoản</h2>
            <input type="hidden" id="email" name="email" value="${email}">
            <div class="form-group activecode">
                <label for="email">Nhập mã xác thực</label>
                <input type="text" id="activecode" name="activecode" placeholder="Enter your active code" value="">
                <br><span class="error" id="activecode_error">${error}</span>
            </div>
            <div class="form-group submit-btn">
                <input type="submit" value="Xác thực">
            </div>
        </form>
    </body>
</html>
