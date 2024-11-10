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
                            <h3 class="fw-bold mb-3"> List</h3>
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
                                    <a href="#">Order Management</a>
                                </li>
                                <li class="separator">
                                    <i class="icon-arrow-right"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">Order List</a>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <form method="get" action="./OrderList" style="display: flex; display: flex; justify-content: center;">
                                            <div style="width: 50%; display: flex; justify-content: space-between;position: absolute; top: 45px">
                                                <div>
                                                    <label>Is Paid: </label>
                                                    <select onchange="this.form.submit()" name="isPaid" class="form-select" style="display: inline-block;width: 150px; margin: 0px 5px;">
                                                        <option value="">All</option>
                                                        <option value="1" ${param.isPaid=="1"?"selected":""}>Paid</option>
                                                        <option value="0" ${param.isPaid=="0"?"selected":""}>Not Paid</option>

                                                    </select>
                                                </div>
                                                <div>
                                                    <label>Status: </label>
                                                    <select onchange="this.form.submit()" name="status" class="form-select" style="display: inline-block;width: 150px; margin: 0px 5px;">
                                                        <option value="">All</option>
                                                        <option value="1" ${param.status=="1"?"selected":""}> Inactive</option>
                                                        <option value="0" ${param.status=="0"?"selected":""}>Active</option>

                                                    </select>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="tables" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Id </th>
                                                        <th>Receiver Name</th>
                                                        <th>Receiver Phone</th>
                                                        <th>Receiver Email</th>
                                                        <th>Total Price</th>
                                                        <th>Created Date</th>
                                                        <th>Is Paid</th>
                                                        <th>IsDeleted</th>
                                                        <th>Delete</th>
                                                        <th>Process</th>
                                                        <th>Update</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="u" items="${pl}">
                                                        <tr>
                                                            <td>${u.saleOrderId}</td>
                                                            <td>${u.receiverName}</td>
                                                            <td>${u.receiverPhone}</td>
                                                            <td>${u.receiverEmail}</td>
                                                            <td>${u.formatPrice()}</td>
                                                            <td>${u.createdDate}</td>
                                                            <td>${u.isPaid()==true?"Paid":"Not paid yet"}</td>
                                                            <td>${u.isDeleted()==false?"Active":"Inactive"}</td>
                                                            <c:if test="${u.isDeleted()==false}">
                                                                <td><a href="./ChangeOrderStatus?pid=${u.saleOrderId}&status=1" class="btn btn-danger">Delete</a></td>
                                                            </c:if>
                                                            <c:if test="${u.isDeleted()==true}">
                                                                <td><a href="./ChangeOrderStatus?pid=${u.saleOrderId}&status=0" class="btn btn-success">Restore</a></td>
                                                            </c:if>
                                                            <td>${u.getProcess()}</td>
                                                            <c:if test="${u.processing<3}">
                                                                <td style="display: flex;flex-direction: row;justify-content: space-between">
                                                                    <a href="./OrderConfirm?oid=${u.saleOrderId}&process=2" class="btn-sm btn-success">Accept</a>
                                                                    <a href="./OrderConfirm?oid=${u.saleOrderId}&process=3" class="btn-sm btn-warning">Delivery</a>
                                                                    <a href="./OrderConfirm?oid=${u.saleOrderId}&process=1" class="btn-sm btn-danger">Reject</a>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${u.processing>=3}">
                                                                <td style="display: flex;flex-direction: row;justify-content: space-between">
                                                                    <button type="button" disabled="" class="btn-sm btn-success">Accept</button>
                                                                    <button type="button" disabled="" class="btn-sm btn-warning">Delivery</button>
                                                                    <button type="button" disabled="" class="btn-sm btn-danger">Reject</button>
                                                                </td>
                                                            </c:if>
                                                            
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


        </div>
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

    </body>
</html>
