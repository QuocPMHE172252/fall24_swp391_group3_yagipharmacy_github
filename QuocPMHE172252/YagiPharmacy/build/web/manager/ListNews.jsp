<%-- 
    Document   : ListNews
    Created on : Sep 22, 2024, 11:37:00 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Kaiadmin - Bootstrap 5 Admin Dashboard</title>
        <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
            />
        <link
            rel="icon"
            href="./assets/img/kaiadmin/favicon.ico"
            type="image/x-icon"
            />

        <!-- Fonts and icons -->
        <script src="./assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {families: ["Public Sans:300,400,500,600,700"]},
                custom: {
                    families: [
                        "Font Awesome 5 Solid",
                        "Font Awesome 5 Regular",
                        "Font Awesome 5 Brands",
                        "simple-line-icons",
                    ],
                    urls: ["./assets/css/fonts.min.css"],
                },
                active: function () {
                    sessionStorage.fonts = true;
                },
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="./assets/css/plugins.min.css" />
        <link rel="stylesheet" href="./assets/css/kaiadmin.min.css" />

        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="./assets/css/demo.css" />
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <jsp:include page="./sidebar.jsp"/>
            <!-- End Sidebar -->
            <div class="main-panel">
                <div class="main-header">
                    <div class="main-header-logo">
                        <!-- Logo Header -->
                        <div class="logo-header" data-background-color="dark">
                            <a href="index.html" class="logo">
                                <img
                                    src="./assets/img/kaiadmin/logo_light.svg"
                                    alt="navbar brand"
                                    class="navbar-brand"
                                    height="20"
                                    />
                            </a>
                            <div class="nav-toggle">
                                <button class="btn btn-toggle toggle-sidebar">
                                    <i class="gg-menu-right"></i>
                                </button>
                                <button class="btn btn-toggle sidenav-toggler">
                                    <i class="gg-menu-left"></i>
                                </button>
                            </div>
                            <button class="topbar-toggler more">
                                <i class="gg-more-vertical-alt"></i>
                            </button>
                        </div>
                        <!-- End Logo Header -->
                    </div>
                    <!-- Navbar Header -->
                    <jsp:include page="./header.jsp"/>
                    <!-- End Navbar -->
                </div>

                <div class="container">
                    <div class="page-inner">
                        <div class="page-header">
                            <h3 class="fw-bold mb-3">List</h3>
                            <ul class="breadcrumbs mb-3">
                                <li class="nav-home">
                                    <a href="#">
                                        <i class="icon-home"></i>
                                    </a>
                                </li>
                                <li class="separator">
                                    <i class="icon-arrow-right"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">News Management</a>
                                </li>
                                <li class="separator">
                                    <i class="icon-arrow-right"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">News List</a>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <table border="0">
                                            <thead>
                                                <tr>
                                                    <th>Danh mục:</th>
                                                    <th>Danh mục con:</th>
                                                    <th>Từ ngày:</th>
                                                    <th>Đến ngày:</th>
                                                    <th>Tiêu đề:</th>
                                                    <th>Thẻ gắn:</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><select class="form-control" id="parent_cate" name="parent_cate" onchange="changeListChild()">
                                                            <option value="all">All</option>
                                                            <c:forEach items="${listParentCates}" var="parent">
                                                                <option value="${parent.newsCategoryId}" ${parent_cate == parent.newsCategoryId.toString()?"selected":""}>${parent.newsCategoryName}</option>
                                                            </c:forEach>
                                                        </select></td>
                                                    <td><select class="form-control" id="cateId" name="cateId">
                                                            <option value="all">All</option>
                                                        </select></td>
                                                    <td><input class="form-control me-2" type="date" name="from" value="${from}"></td>
                                                    <td><input class="form-control me-2" type="date" name="to" value="${to}"></td>
                                                    <td><input class="form-control me-2" type="search" placeholder="Tiêu đề" name="title" value="${title}"></td>
                                                    <td><input class="form-control me-2" type="search" placeholder="Thẻ được gắn" name="hashtag" value="${hashtag}"></td>
                                                    <td><button class="btn btn-outline-success" type="submit">Search</button></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="tables" class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Ảnh bìa</th>
                                                        <th scope="col">Tiêu đề</th>
                                                        <th scope="col">Xóa/Khôi phục</th>
                                                        <th scope="col">Chỉnh sửa</th>
                                                        <th scope="col">Trạng thái kiểm duyệt</th>
                                                        <th scope="col">Kiểm duyệt</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${data}" var="e">
                                                        <tr>
                                                            <th><img src="${e.newsImage}" alt="alt" height="100px" width="100px"></th>
                                                            <td><a target="_blank" href="../NewsDetail?nid=${e.newsId}">${e.newsTitle}</a></td>
                                                            <td><button type="button" class='btn ${e.isDeleted()?"btn-success":"btn-danger"}'>${e.isDeleted()?"Active":"Delete"}</button></td>
                                                            <td><a class="btn btn-success" href="UpdateNews?newsId=${e.newsId}" target="_blank">Update</a></td>
                                                            <td><div>${e.getRejectName()}</div></td>
                                                            <td><a class="btn btn-success" href="PublishNew?newsId=${e.newsId}" target="_blank">Publish New</a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <!--   Core JS Files   -->
    <script src="./assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="./assets/js/core/popper.min.js"></script>
    <script src="./assets/js/core/bootstrap.min.js"></script>

    <!-- jQuery Scrollbar -->
    <script src="./assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

    <!-- Chart JS -->
    <script src="./assets/js/plugin/chart.js/chart.min.js"></script>

    <!-- jQuery Sparkline -->
    <script src="./assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

    <!-- Chart Circle -->
    <script src="./assets/js/plugin/chart-circle/circles.min.js"></script>

    <!-- Datatables -->
    <script src="./assets/js/plugin/datatables/datatables.min.js"></script>

    <!-- Bootstrap Notify -->
    <!--<script src="./assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>-->

    <!-- jQuery Vector Maps -->
    <script src="./assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
    <script src="./assets/js/plugin/jsvectormap/world.js"></script>

    <!-- Sweet Alert -->
    <script src="./assets/js/plugin/sweetalert/sweetalert.min.js"></script>

    <!-- Kaiadmin JS -->
    <script src="./assets/js/kaiadmin.min.js"></script>

    <!-- Kaiadmin DEMO methods, don't include it in your project! -->
    <script src="./assets/js/setting-demo.js"></script>
    <script src="./assets/js/demo.js"></script>
    <script>
                                $("#lineChart").sparkline([102, 109, 120, 99, 110, 105, 115], {
                                    type: "line",
                                    height: "70",
                                    width: "100%",
                                    lineWidth: "2",
                                    lineColor: "#177dff",
                                    fillColor: "rgba(23, 125, 255, 0.14)",
                                });

                                $("#lineChart2").sparkline([99, 125, 122, 105, 110, 124, 115], {
                                    type: "line",
                                    height: "70",
                                    width: "100%",
                                    lineWidth: "2",
                                    lineColor: "#f3545d",
                                    fillColor: "rgba(243, 84, 93, .14)",
                                });

                                $("#lineChart3").sparkline([105, 103, 123, 100, 95, 105, 115], {
                                    type: "line",
                                    height: "70",
                                    width: "100%",
                                    lineWidth: "2",
                                    lineColor: "#ffa534",
                                    fillColor: "rgba(255, 165, 52, .14)",
                                });
    </script>
    <script>
        $(document).ready(function () {
            $('#tables').DataTable();
        });
    </script>
    <script>
        var childListJson = '${listChildJson}';
        var listChild = JSON.parse(childListJson);
        changeListChild();
        function changeListChild() {
            var parentId = document.getElementById("parent_cate").value;
            document.getElementById("cateId").innerHTML = '<option value="all">All</option>';
            listChild.forEach((child) => {
                if (child.newsCategoryParentId == parentId) {
                    let selectOption = '<option value="' + child.newsCategoryId + '">' + child.newsCategoryName + '</option>';
                    if ("${cateId}" == child.newsCategoryId) {
                        selectOption = '<option selected value="' + child.newsCategoryId + '">' + child.newsCategoryName + '</option>';
                    }
                    document.getElementById("cateId").innerHTML += selectOption;
                }
            });
        }
    </script>
</html>
