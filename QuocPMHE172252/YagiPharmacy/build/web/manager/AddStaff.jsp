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
                                    <h3>Create New Staff</h3>
                                </div>
                                <div class="card-body">
                                    <form method="post" action="Add-UpdateStaff" id="createStaffForm" class="row">
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="user_phone_email">Email/Số điện thoại</label>
                                            <input type="text" class="form-control" name="user_phone_email" id="user_phone_email" value="${findingUser==null?param.user_phone_email:findingUser.userEmail}" placeholder="Enter phone number" required>
                                            <p style="color: red">${messErrorPhone}</p>

                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="staff_major">Chuyên ngành</label>
                                            <select id="staff_major" name="staff_major" class="form-control">
                                                <c:if test="${findingStaff==null}">
                                                    <c:forEach items="${majorCategorys}" var="mCate">
                                                        <option value="${mCate.getMajorCategoryId()}" ${param.staff_major==mCate.getMajorCategoryId()?"selected":""}>${mCate.getMajorCategoryName()}</option>
                                                    </c:forEach>
                                                </c:if>
                                                
                                                <c:if test="${findingStaff!=null}">
                                                    <c:forEach items="${majorCategorys}" var="mCate">
                                                        <option value="${mCate.getMajorCategoryId()}" ${findingStaff.staffMajorId==mCate.getMajorCategoryId()?"selected":""}>${mCate.getMajorCategoryName()}</option>
                                                    </c:forEach>
                                                </c:if>            
                                            </select>

                                        </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="staff_degree">Học vị</label>
                                                <input type="text" class="form-control" name="staff_degree" id="staff_degree" value="${findingStaff==null?param.staff_degree:findingStaff.staffDegree}" placeholder="" required>
                                            </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="staff_education">Học vấn</label>
                                            <textarea type="text" class="form-control" name="staff_education" id="staff_education" required rows="20"><c:if test="${findingStaff==null}">${param.staff_education}</c:if><c:if test="${findingStaff!=null}">${findingStaff.staffEducation}</c:if></textarea>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="staff_description">Miêu tả ngắn</label>
                                            <textarea class="form-control" name="staff_description" id="staff_description" required rows="20"><c:if test="${findingStaff==null}">${param.staff_description}</c:if><c:if test="${findingStaff!=null}">${findingStaff.staffDescription}</c:if></textarea>
                                        </div>

                                        <div class="form-group  mb-3 col-md-6">
                                            <label for="staff_experience">Kinh nghiệm làm việc</label>
                                            <textarea class="form-control" name="staff_experience" id="staff_experience" required rows="20"><c:if test="${findingStaff==null}">${param.staff_experience}</c:if><c:if test="${findingStaff!=null}">${findingStaff.staffExperience}</c:if></textarea>
                                        </div>
                                        <p>${errorMessage}</p>
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
            document.getElementById("staff_major").value = '${param.staff_major}';
            <c:if test="${error!=null}">
            window.alert('${error}');
            </c:if>
            <c:if test="${findingUser!=null}">
                document.getElementById("user_phone_email").setAttribute("readonly",true);
                
            </c:if>
            <c:if test="${findingStaff!=null}">
                document.getElementById("staff_major").value = '${findingStaff.staffMajorId}';
            </c:if>
            <c:if test="${findingStaff==null}">
                document.getElementById("staff_major").value = '${param.staff_major}';
            </c:if>
            // Validate date of birth to ensure it's not greater than today
            function validateDateOfBirth(dateOfBirth) {
                const today = new Date();
                const inputDate = new Date(dateOfBirth);
                return inputDate <= today;
            }

            // Validate the avatar to check if it's an image and smaller than 5MB
            function validateAvatar(file) {
                const validImageTypes = ['image/jpeg', 'image/png', 'image/gif'];
                const maxFileSize = 5 * 1024 * 1024; // 5MB

                if (!validImageTypes.includes(file.type)) {
                    alert("Please upload a valid image (JPG, PNG, GIF).");
                    return false;
                }

                if (file.size > maxFileSize) {
                    alert("Avatar file size must be less than 5MB.");
                    return false;
                }

                return true;
            }

            // Form validation
            function validateForm() {
                const phone = document.getElementById('user_phone').value;
                const dateOfBirth = document.getElementById('date_of_birth').value;
                const avatarFile = document.getElementById('user_avatar').files[0];
                if (!isValidPhoneNumber(phone)) {
                    alert("Phone number not valid try again!");
                    return false;
                }

                if (!validateDateOfBirth(dateOfBirth)) {
                    alert("Date of birth cannot be a future date.");
                    return false;
                }

                if (avatarFile && !validateAvatar(avatarFile)) {
                    return false;
                }

                return true; // Submit form if all validations pass
            }

            // Avatar preview and encoding to base64
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
