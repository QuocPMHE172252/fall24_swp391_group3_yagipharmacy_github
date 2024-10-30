<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
                            <h3 class="fw-bold mb-3">Danh sách nhập hàng</h3>
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
                                    <a href="#">Quản lý danh sách nhập</a>
                                </li>
                                <li class="separator">
                                    <i class="icon-arrow-right"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">Quản lý danh sách nhập</a>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <a href="./CreateImportOrder" class="btn btn-primary">Tạo yêu cầu nhập hàng</a>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <form method="get" action="./ImportOrderList" style="display: flex;flex-direction: row;justify-content: center;">
                                                <div style="display: flex;flex-direction: row;">
                                                    <div>
                                                        <label>Trạng thái đơn hàng: </label>
                                                        <select onchange="this.form.submit()" name="reject_status" class="form-select" style="display: inline-block;width: 150px; margin: 0px 5px;">
                                                            <option value="all">All</option>
                                                            <option value="0" ${param.reject_status=="0"?"selected":""}>Processing</option>
                                                            <option value="1" ${param.reject_status=="1"?"selected":""}>Approve</option>
                                                            <option value="2" ${param.reject_status=="2"?"selected":""}>Rejected</option>
                                                        </select>
                                                    </div>&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <div>
                                                        <label>Xóa: </label>
                                                        <select onchange="this.form.submit()" name="delete_status" class="form-select" style="display: inline-block;width: 150px; margin: 0px 5px;">
                                                            <option value="all">All</option>
                                                            <option value="0" ${param.delete_status=="0"?"selected":""}>Chưa xóa</option>
                                                            <option value="1" ${param.delete_status=="1"?"selected":""}>Đã xóa</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </form>
                                            <table id="tables" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Mã đơn nhập</th>
                                                        <th>Ngày tạo</th>
                                                        <th>Ngày chấp thuận</th>
                                                        <th>Ngày nhận dự kiến</th>
                                                        <th>Trạng thái đơn hàng</th>
                                                        <th>Xác nhận</th>
                                                        <th>Xóa</th>
                                                        <th>Xóa/Khôi phục</th>
                                                        <th>Cập nhật</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="imp_order" items="${data}">
                                                        <tr>
                                                            <td>${imp_order.importOrderId}</td>
                                                            <td>${imp_order.importOrderCode}</td>
                                                            <td>${imp_order.formatDate(imp_order.createdDate)}</td>
                                                            <td>${imp_order.formatDate(imp_order.approvedDate)}</td>
                                                            <td>${imp_order.formatDate(imp_order.importExpectedDate)}</td>

                                                            <c:if test="${imp_order.isAccepted==null}">
                                                                <td style="color: orange">Đang chờ xác nhận</td>
                                                            </c:if>
                                                            <c:if test="${imp_order.isAccepted!=null}">
                                                                <td style="color: ${imp_order.isAccepted.booleanValue()?"green":"red"}">${imp_order.isAccepted.booleanValue()?"Đã xác nhận":"Đã hủy đơn"}</td>
                                                            </c:if>
                                                            <c:if test="${imp_order.isAccepted==null}">
                                                                <td style="display: flex;flex-direction: row;">
                                                                    <a class="btn-sm btn-success" href="ApproveImportOrder?import_order_id=${imp_order.importOrderId}&status=1">Approve</a>
                                                                    <a href="#" class="btn-sm btn-danger" onclick="showRejectModal(${imp_order.importOrderId})" data-toggle="modal" data-target="#myModal">Reject</a>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${imp_order.isAccepted!=null}">
                                                                    <td style="display: flex;flex-direction: row">
                                                                        <a class="btn-sm" style="background-color: gray">Approve</a>
                                                                        <a class="btn-sm">Reject</a>
                                                                    </td>
                                                            </c:if>
                                                            <td>${imp_order.isDeleted()?"yes":"no"}</td>
                                                            <td><a href="./DeleteImportOrder?id=${imp_order.importOrderId}" class="btn btn-warning">${imp_order.isDeleted()?"Khôi phục":"Xóa"}</a></td>
                                                            <td><a href="#" class="btn btn-warning">Cập nhật</a></td>
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
            <!-- The Modal -->
            <div class="modal" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Xác nhận từ chối</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal Body -->
                        <div class="modal-body">
                            <form action="ApproveImportOrder" method="get" class="form" id="reject_form">
                                <input  type="text" name="import_order_id" id="import_order_id" hidden="">
                                <input type="text" name="status" id="status" value="0" hidden="">
                                <label for="reject_reason">Lý do từ chối*:</label>
                                <textarea class="form-control" type="text" name="reject_reason" id="reject_reason" required=""></textarea>
                            </form>
                        </div>

                        <!-- Modal Footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" onclick="rejectImportOrder()">Xác nhận</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                        </div>

                    </div>
                </div>
            </div>

            <footer class="footer">
                <div class="container-fluid d-flex justify-content-between">
                    <nav class="pull-left">
                        <ul class="nav">
                            <li class="nav-item">
                                <a class="nav-link" >
                                    Group 3
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#"> Help </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#"> Licenses </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="copyright">
                        2024, made with <i class="fa fa-heart heart text-danger"></i> by Group 3
                    </div>
                    <div>
                        Distributed by
                        <a target="_blank" href="https://themewagon.com/">SWP391</a>.
                    </div>
                </div>
            </footer>
        </div>
        <!--   Core JS Files   -->
        <script src="./assets/js/core/jquery-3.7.1.min.js"></script>
        <script src="./assets/js/core/popper.min.js"></script>
        <script src="./assets/js/core/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 


        <!-- jQuery Scrollbar -->
        <script src="assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

        <!-- Chart JS -->
        <script src="assets/js/plugin/chart.js/chart.min.js"></script>

        <!-- jQuery Sparkline -->
        <script src="assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

        <!-- Chart Circle -->
        <script src="assets/js/plugin/chart-circle/circles.min.js"></script>

        <!-- Datatables -->
        <script src="assets/js/plugin/datatables/datatables.min.js"></script>

        <!-- Bootstrap Notify -->
        <!--<script src="./assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>-->

        <!-- jQuery Vector Maps -->
        <script src="assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
        <script src="assets/js/plugin/jsvectormap/world.js"></script>

        <!-- Sweet Alert -->
        <script src="assets/js/plugin/sweetalert/sweetalert.min.js"></script>

        <!-- Kaiadmin JS -->
        <script src="assets/js/kaiadmin.min.js"></script>

        <!-- Kaiadmin DEMO methods, don't include it in your project! -->
        <script src="assets/js/setting-demo.js"></script>
        <script src="assets/js/demo.js"></script>

        <!-- Thư viện JS jQuery -->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->
        <!-- Thư viện JS Select2 -->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>-->
        <!-- Khởi tạo Select2 -->
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
            function rejectImportOrder() {
                document.getElementById("reject_form").submit();
            }
            function showRejectModal(imp_id) {
                document.getElementById("reject_reason").value = '';
                document.getElementById("import_order_id").value = imp_id + "";
                document.getElementById("status").value = "0";
            }
        </script>

    </body>
</html>
