<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <title>Kaiadmin - Bootstrap 5 Admin Dashboard</title>
            <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
            <link rel="icon" href="./assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

            <!-- Fonts and icons -->
            <script src="./assets/js/plugin/webfont/webfont.min.js"></script>
            <script>
                WebFont.load({
                    google: { families: ["Public Sans:300,400,500,600,700"] },
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
                <jsp:include page="./sidebar.jsp" />
                <!-- End Sidebar -->

                <div class="main-panel">
                    <div class="main-header">
                        <div class="main-header-logo">
                            <!-- Logo Header -->
                            <div class="logo-header" data-background-color="dark">
                                <a href="index.html" class="logo">
                                    <img src="./assets/img/kaiadmin/logo_light.svg" alt="navbar brand"
                                        class="navbar-brand" height="20" />
                                </a>
                                <div class="nav-toggle">
                                    <button class="btn btn-toggle toggle-sidebar">
                                        <i class="gg-menu-right"></i>
                                    </button>
                                    <button class="btn btn-toggle sidenav-toggler" data-toggle="collapse"
                                        data-target="#navbarNav">
                                        <i class="gg-menu-left"></i>
                                    </button>
                                </div>
                                <button class="topbar-toggler more" data-toggle="collapse" data-target="#navbarNav">
                                    <i class="gg-more-vertical-alt"></i>
                                </button>
                            </div>
                            <!-- End Logo Header -->
                        </div>
                        <!-- Navbar Header -->
                        <jsp:include page="./header.jsp" />
                        <!-- End Navbar -->
                    </div>

                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h3>Create New Product</h3>
                                    </div>
                                    <div class="card-body">
                                        <form method="post" action="AddProduct" id="createProductForm" class="row">
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_code">Mã đăng kí sản phẩm*</label>
                                                <input type="text" class="form-control" name="product_code"
                                                    id="product_code" value="" placeholder="Nhập mã đăng kí sản phẩm"
                                                    required>
                                                <p style="color: red"></p>

                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_name">Tên sản phẩm*</label>
                                                <input type="text" class="form-control" name="product_name"
                                                    id="product_name" value="" placeholder="Nhập tên sản phẩm" required>
                                                <p style="color: red"></p>

                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_category">Danh mục</label>
                                                <select class="form-control" name="product_category"
                                                    id="product_category" value="" placeholder="Chọn danh mục sản phẩm"
                                                    required>
                                                    <c:forEach items="${productCategorys}" var="pc">
                                                        <option value="${pc.productCategoryId}">
                                                            ${pc.productCategoryName}

                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <p style="color: red"></p>

                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="dosage_form">Dạng bào chế*</label>
                                                <input type="text" class="form-control" name="dosage_form"
                                                    id="dosage_form" placeholder="Nhập dạng bào chế ở đây" required>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_specification">Quy cách*</label>
                                                <input type="text" class="form-control" name="product_specification"
                                                    value="" id="product_specification"
                                                    placeholder="Nhập đặc điểm kĩ thuật" required>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_target">Đối tượng sử dụng*</label>
                                                <input type="text" class="form-control" name="product_target" value=""
                                                    id="product_target" placeholder="Chỉ định và chống chỉ định"
                                                    required>
                                            </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_desciption">Thông tin thêm</label>
                                                <textarea class="form-control" id="product_desciption"
                                                    name="product_desciption" rows="5" cols="10"></textarea>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="excipients">Thành phần*</label>
                                                <select class="form-control-sm" type="text" id="excipients"
                                                    name="excipients" required>
                                                </select> <button class="btn-dropdown btn-success" type="button"
                                                    onclick="addNewExcipient()">+</button><br>
                                                <table class="form-group table">
                                                    <tbody id="exs_in_drug">
                                                        <tr>
                                                            <th>Tá dược</th>
                                                            <th>Liều lượng</th>
                                                            <th>Đơn vị</th>
                                                            <th>Xóa</th>
                                                        </tr>
                                                    </tbody>

                                                </table>
                                                <input type="text" class="form-control" name="excipients_string"
                                                    value="" id="excipients_string" required readonly>
                                            </div>

                                            <div class="form-group  mb-3 col-md-6">
                                                <label>Nhà cung cấp*</label><br />
                                                <select class="form-control-sm" id="suplier_id" name="suplier_id"
                                                    required>
                                                    <c:forEach items="${suppliers}" var="supplier">
                                                        <option value="${supplier.supplierId}">${supplier.supplierName}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group  mb-3 col-md-6">
                                                <label>Quốc gia*</label><br />
                                                <select class="form-control-sm" id="product_country_code"
                                                    name="product_country_code" required>
                                                </select>
                                            </div>
                                            <div class="form-group  mb-3 col-md-6">
                                                <label>Thuốc kê theo đơn bác sĩ*</label><br />
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" name="is_prescription"
                                                        id="is_prescription_yes" value="0" checked />
                                                    <label class="form-check-label"
                                                        for="is_prescription_yes">Yes</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" name="is_prescription"
                                                        id="is_prescription_no" value="1" />
                                                    <label class="form-check-label" for="is_prescription_no">No</label>
                                                </div>
                                            </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="units">Đơn vị lưu trữ*</label>
                                                <select class="form-control-sm" type="text" id="units" name="units"
                                                    required>
                                                </select> <button class="btn-dropdown btn-success" type="button"
                                                    onclick="addNewUnit()">+</button><br>
                                                <table class="form-group table">
                                                    <tbody id="units_in_drug">
                                                        <tr>
                                                            <th>Tên đơn vị lưu trữ</th>
                                                            <th>Hệ số đơn vị con</th>
                                                            <th>Được bán</th>
                                                            <th>Giá bán</th>
                                                            <th>Xóa</th>
                                                        </tr>
                                                    </tbody>

                                                </table>
                                                <input type="text" class="form-control" name="units_string" value=""
                                                    id="units_string" required readonly>
                                            </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_image">Avatar</label>
                                                <input type="file" class="form-control" id="product_image"
                                                    accept="image/*" required>
                                                <input type="text" class="form-control" id="product_image_submit"
                                                    style="" name="product_image_submit">
                                            </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="date_of_birth">Demo Img</label>
                                                <img id="avatarPreview" class="mt-3" src="" alt="Avatar Preview"
                                                    style="max-width: 300px; display: none;">
                                            </div>
                                            <p>${errorMessage}</p>
                                            <div class="form-group mb-3 col-md-12">
                                                <button type="submit" class="btn btn-success" style="width: 100px"
                                                    onclick="submitForm()">Submit</button>
                                            </div>
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
                                        <a class="nav-link">Group 3</a>
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
                                Distributed by <a target="_blank" href="">SWP391</a>.
                            </div>
                        </div>
                    </footer>
                </div>
            </div>

            <!-- Core JS Files -->
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

            <!-- Sweet Alert -->
            <script src="./assets/js/plugin/sweetalert/sweetalert.min.js"></script>

            <!-- Kaiadmin JS -->
            <script src="./assets/js/kaiadmin.min.js"></script>

            <!-- Kaiadmin DEMO methods, don't include it in your project! -->
            <script src="./assets/js/setting-demo.js"></script>
            <script src="./assets/js/demo.js"></script>

            <script>
                $(document).ready(function () {
                    $('#tables').DataTable();
                });

                $('.sidenav-toggler').on('click', function () {
                    $('#navbarNav').toggleClass('collapse');
                });

                $('.topbar-toggler').on('click', function () {
                    $('#navbarNav').toggleClass('collapse');
                });
            </script>
        </body>

        </html>