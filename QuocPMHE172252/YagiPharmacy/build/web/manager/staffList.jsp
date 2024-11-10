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
                                    <a href="#">Account Management</a>
                                </li>
                                <li class="separator">
                                    <i class="icon-arrow-right"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">Staff List</a>
                                </li>
                            </ul>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <a href="/YagiPharmacy/manager/Add-UpdateStaff" class="btn btn-primary">Add Staff</a>
                                        <form method="get" action="./StaffList" style="display: flex; display: flex; justify-content: center;">
                                            <div style="width: 50%; display: flex; justify-content: space-between;position: absolute; top: 85px">
                                                <div>
                                                    <label>Category: </label>
                                                    <select name="major"  onchange="this.form.submit()" class="form-select" style="display: inline-block;width: 150px;margin: 0px 5px;">
                                                        <option value="">All</option>
                                                        <c:forEach var="mc" items="${ml}">
                                                            <option value="${mc.majorCategoryName}" ${param.major==mc.majorCategoryName?"selected":""}>${mc.majorCategoryName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div>
                                                    <label>Status: </label>
                                                    <select onchange="this.form.submit()" name="is_deleted" class="form-select" style="display: inline-block;width: 150px; margin: 0px 5px;">
                                                        <option value="">All</option>
                                                        <option value="0" ${param.is_deleted=="0"?"selected":""}>Active</option>
                                                            <option value="1" ${param.is_deleted=="1"?"selected":""}>Inactive</option>

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
                                                        <th>Code</th>
                                                        <th>Name</th>

                                                        <th>Major</th>
                                                        <th>Gender</th>
                                                        <th>Degree</th>
                                                        <th>IsDeleted</th>
                                                        <th>Delete</th>
                                                        <th>Update</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="s" items="${st}">
                                                        <tr>
                                                            <td>${s.staffId}</td>
                                                            <td>${s.userId}</td>
                                                            <td>${s.user.userName}</td>
                                    
                                                            <td>${s.staffMajor.getMajorCategoryName()}</td>
                                                            <td>${s.gender==true?"Male":"Female"}</td>
                                                            <td>${s.staffDegree}</td>
                                                            <td>${s.isDeleted()==false?"Active":"Inactive"}</td>
                                                            <c:if test="${s.isDeleted()==false}">
                                                                <td><a href="../admin/ChangeStaffStatus?sid=${s.staffId}&is_deleted=1" class="btn btn-danger">Delete</a></td>
                                                            </c:if>
                                                            <c:if test="${s.isDeleted()==true}">
                                                                <td><a href="../admin/ChangeStaffStatus?sid=${s.staffId}&is_deleted=0" class="btn btn-success">Restore</a></td>
                                                            </c:if>
                                                            <td><a href="./Add-UpdateStaff?u_id=${s.user.userId}" class="btn btn-warning">Update</a></td>
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