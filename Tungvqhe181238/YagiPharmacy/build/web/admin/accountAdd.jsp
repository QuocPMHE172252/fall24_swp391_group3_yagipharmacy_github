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
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3>Create New Account</h3>
                                </div>
                                <div class="card-body">
                                    <form method="post" action="CreateAccount" id="createAccountForm" class="row" >
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_name">Username</label>
                                            <input type="text" class="form-control" name="user_name" id="user_name" placeholder="Enter username" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_phone">Phone</label>
                                            <input type="text" class="form-control" name="user_phone" id="user_phone" placeholder="Enter phone number" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_email">Email</label>
                                            <input type="email" class="form-control" name="user_email" id="user_email" placeholder="Enter email" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_password">Password</label>
                                            <input type="password" class="form-control" name="user_password" id="user_password" placeholder="Enter password" required>
                                        </div>



                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_bank">Bank</label>
                                            <input type="text" class="form-control" name="user_bank" id="user_bank" placeholder="Enter bank name" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_bank_code">Bank Code</label>
                                            <input type="text" class="form-control" name="user_bank_code" id="user_bank_code" placeholder="Enter bank code" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="specific_address">Address</label>
                                            <input type="text" class="form-control" name="specific_address" id="specific_address" placeholder="Enter address" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="date_of_birth">Date of Birth</label>
                                            <input type="date" class="form-control" name="date_of_birth" id="date_of_birth" required>
                                        </div>

                                        <div class="form-group  mb-3 col-md-6">
                                            <label>Is Active</label><br />
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="is_active" id="isActiveYes" value="1" checked />
                                                <label class="form-check-label" for="isActiveYes">Yes</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="is_active" id="isActiveNo" value="0" />
                                                <label class="form-check-label" for="isActiveNo">No</label>
                                            </div>
                                        </div>
                                        <div class="form-group  mb-3 col-md-6">
                                            <label>Is Deleted</label><br />
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="is_deleted" id="isDeletedYes" value="0" checked />
                                                <label class="form-check-label" for="isDeletedYes">No</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="is_deleted" id="isDeletedNo" value="1" />
                                                <label class="form-check-label" for="isDeletedNo">Yes</label>
                                            </div>
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_role">Role</label>
                                            <select class="form-select" name="user_role" id="user_role" required>
                                                <option value="1">Admin</option>
                                                <option value="2">Manager</option>
                                                <option value="3">Staff</option>
                                                <option value="4">Customer</option>
                                            </select>
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_avatar">Avatar</label>
                                            <input type="file" class="form-control" id="user_avatar" accept="image/*" required>
                                            <input type="text" class="form-control" id="user_avatar_submit" style="display: none" name="user_avatar">
                                            
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="date_of_birth">Demo Img</label>
                                            <img id="avatarPreview" class="mt-3" src=""  alt="Avatar Preview" style="max-width: 300px; display: none;">
                                        </div>
                                        <p style="color: red;">${errorMessage}</p>
                                        <div class="form-group mb-3 col-md-12">
                                            <button type="submit" class="btn btn-success" style="width: 100px">Submit</button>
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
            document.getElementById('user_avatar').addEventListener('change', function (event) {
                const file = event.target.files[0];
                const reader = new FileReader();

                reader.onload = function (e) {
                    const base64Image = e.target.result;
                    document.getElementById('avatarPreview').src = base64Image;
                    document.getElementById('avatarPreview').style.display = 'block';
                    document.getElementById('user_avatar_submit').value = base64Image;
                };

                reader.readAsDataURL(file);
            });

//            document.getElementById('createAccountForm').addEventListener('submit', function (event) {
//                event.preventDefault();
//                const formData = {
//                    user_name: document.getElementById('user_name').value,
//                    user_phone: document.getElementById('user_phone').value,
//                    user_email: document.getElementById('user_email').value,
//                    user_password: document.getElementById('user_password').value,
//                    user_avatar: document.getElementById('avatarPreview').src, 
//                    user_bank: document.getElementById('user_bank').value,
//                    user_bank_code: document.getElementById('user_bank_code').value,
//                    specific_address: document.getElementById('specific_address').value,
//                    date_of_birth: document.getElementById('date_of_birth').value,
//                    is_active: document.querySelector('input[name="is_active"]:checked').value,
//                    is_deleted: document.querySelector('input[name="is_deleted"]:checked').value,
//                    role_level: document.getElementById('user_role').value
//                };
//                console.log('Form data:', formData);
//                fetch('./CreateAccount', {
//                    method: 'POST',
//                    headers: {
//                        'Content-Type': 'application/json',
//                    },
//                    body: JSON.stringify(formData),
//                })
//                        .then(response => {
//                            if (response.ok) {
//                                window.location.href = './AccountList';
//                            } else {
//                                throw new Error('Network response was not ok.');
//                            }
//                        })
//                        .catch(error => {
//                            console.error('There was a problem with the fetch operation:', error);
//                            // Optionally, show an error message to the user
//                        });
//            });

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
