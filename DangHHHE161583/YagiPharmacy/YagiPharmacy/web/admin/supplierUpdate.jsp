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
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h3>Update Supplier</h3>
                                </div>
                                <div class="card-body">
                                    <form action="UpdateSupllier" method="post" onsubmit="return validateForm()">
                                        <input type="hidden" name="supplierId" value="${supplier.supplierId}">

                                        <label for="supplierCode">Supplier Code*</label>
                                        <input type="text" class="form-control" id="supplierCode" name="supplierCode" value="${supplier.supplierCode}" required><br><br>

                                        <label for="supplierName">Supplier Name*</label>
                                        <input type="text" class="form-control" id="supplierName" name="supplierName" value="${supplier.supplierName}" required><br><br>

                                        <label for="supplierCountryCode">Country Code*</label>
                                        <input type="text"class="form-control" id="supplierCountryCode" name="supplierCountryCode" value="${supplier.supplierCountryCode}" required><br><br>

                                        <label for="supplierPhone">Supplier Phone*</label>
                                        <input type="text" id="supplierPhone"class="form-control" name="supplierPhone"  value="${supplier.supplierPhone}" required><br><br>

                                        <label for="supplierEmail">Supplier Email*</label>
                                        <input type="email" id="supplierEmail" class="form-control" name="supplierEmail" value="${supplier.supplierEmail}" required><br><br>

                                        <label for="isDeleted">Is Deleted*</label><br>
                                        <input type="radio" class="form-check-input" id="isDeletedYes" name="isDeleted" value="true" ${supplier.isDeleted() ? "checked" : ""}> Yes
                                        <input type="radio" class="form-check-input" id="isDeletedNo" name="isDeleted" value="false" ${!supplier.isDeleted() ? "checked" : ""}> No<br><br>

                                        <input type="submit" class="btn btn-primary" value="Update Supplier">
                                    </form>
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
                            <a target="_blank" href="">SWP391</a>.
                        </div>
                    </div>
                </footer>
            </div>


        </div>
        <script>

            function handleSupplierChange() {
                const selectElement = document.getElementById("productSupplierSelect");
                const selectedOption = selectElement.options[selectElement.selectedIndex];
                const categoryLevel = selectedOption.getAttribute('data-category-level');
                const categoryLevelInt = parseInt(categoryLevel);
                document.getElementById('productSupplierLevel').value = categoryLevelInt + 1;

            }

        </script>

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
            let myList = [];

            // Form validation
            function validateForm() {

            <c:forEach var="p" items="${pl}">
                <c:if test="${p.supplierCode!= supplier.supplierCode}">
                myList.push('${p.supplierCode}');
                </c:if>
            </c:forEach>
                var code = document.getElementById('supplierCode').value;
                if (myList.includes(code)) {
                    alert("This supplier code already exist!");
                    return false;
                }

                const phone = document.getElementById('supplierPhone').value;
                if (!isValidPhoneNumber(phone)) {
                    alert("Phone number not valid try again!");
                    return false;
                }
                return true; // Submit form if all validations pass
            }



            function isValidPhoneNumber(phoneNumber) {
                const cleanedNumber = phoneNumber.replace(/\s+/g, '').replace(/-/g, '');
                const phoneRegex = /^(84|0[35789])+([0-9]{8})\b/;
                if (!/^(\+|\d)/.test(cleanedNumber)) {
                    return false;
                }
                if (!phoneRegex.test(phoneNumber)) {
                    return false;
                }
                if (cleanedNumber.length < 10 || cleanedNumber.length > 15) {
                    return false;
                }
                return true;
            }

            function isValidEmail(email) {
                const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

                return emailRegex.test(email);
            }
        </script>
        <script>
            $(document).ready(function () {
                $('#tables').DataTable();
            });
        </script>


    </body>
</html>
