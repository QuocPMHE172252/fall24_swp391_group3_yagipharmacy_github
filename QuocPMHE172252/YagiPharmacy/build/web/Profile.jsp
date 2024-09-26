<%-- 
    Document   : Profile
    Created on : Sep 22, 2024, 1:46:43 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">


        <title>Table user information - Bootdey.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            .inf-content {
                border: 1px solid #DDDDDD;
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                box-shadow: 7px 7px 7px rgba(0, 0, 0, 0.3);
            }
        </style>
    </head>

    <body>
        <div class="container bootstrap snippets bootdey">
            <div class="panel-body inf-content">
                <div class="row">
                    <div class="col-md-4">
                        <img alt style="width:300px; height: 300px" title class="img-circle img-thumbnail isTooltip"
                             src="${sessionScope.userAuth.userAvatar}" data-original-title="Usuario">
                    </div>
                    <div class="col-md-6">
                        <strong>Thông tin</strong><br>
                        <div class="table-responsive">
                            <table class="table table-user-information">
                                <tbody>
                                    <tr>
                                        <td>
                                            <strong>
                                                <span class="glyphicon glyphicon-earphone text-primary"></span>
                                                Số điện thoại
                                            </strong>
                                        </td>
                                        <td class="text-primary">
                                            ${sessionScope.userAuth.userPhone}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>
                                                <span class="glyphicon glyphicon-user  text-primary"></span>
                                                Họ và Tên
                                            </strong>
                                        </td>
                                        <td class="text-primary">
                                            ${sessionScope.userAuth.userName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>
                                                <span class="glyphicon glyphicon-envelope text-primary"></span>
                                                Email
                                            </strong>
                                        </td>
                                        <td class="text-primary">
                                            ${sessionScope.userAuth.userEmail}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>
                                                <span class="glyphicon glyphicon-map-marker text-primary"></span>
                                                Địa chỉ
                                            </strong>
                                        </td>
                                        <td class="text-primary">
                                            Tỉnh/thành phố ${sessionScope.userAuth.userProvince}, quận/huyện ${sessionScope.userAuth.userDistrict}, xã/phường ${sessionScope.userAuth.userCommune}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>
                                                <span class="glyphicon glyphicon-calendar text-primary"></span>
                                                Ngày sinh
                                            </strong>
                                        </td>
                                        <td class="text-primary">
                                            ${sessionScope.userAuth.dateOfBirth!=null?sessionScope.userAuth.dateOfBirth.toString():"Chưa cập nhật"}
                                        </td>
                                    </tr>
                                    <tr></tr>
                                <td>
                                    <a class="btn btn-info" href="ChangeProfile" target="_blank">Chỉnh sửa</a>
                                </td>
                                <td>
                                    <a class="btn btn-danger" href="ChangePassword" target="_blank">Thay đổi mật khẩu</a>
                                </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        </script>
    </body>
</html>
