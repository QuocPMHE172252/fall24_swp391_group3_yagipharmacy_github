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
                                    <form method="post" action="AddProduct" id="createProductForm" class="row">
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_code">Mã đăng kí sản phẩm</label>
                                            <input type="text" class="form-control" name="product_code" id="product_code" value="" placeholder="Nhập mã đăng kí sản phẩm" required>
                                            <p style="color: red"></p>

                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_name">Tên sản phẩm</label>
                                            <input type="text" class="form-control" name="product_name" id="product_name" value="" placeholder="Nhập tên sản phẩm" required>
                                            <p style="color: red"></p>

                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_category">Danh mục</label>
                                            <select class="form-control" name="product_category" id="product_category"  value="" placeholder="Chọn danh mục sản phẩm" required>
                                                <c:forEach items="${productCategorys}" var="pc">
                                                    <option value="${pc.productCategoryId}">${pc.productCategoryName}

                                                    </option>
                                                </c:forEach>
                                            </select>
                                            <p style="color: red"></p>

                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="dosage_form">Dạng bào chế</label>
                                            <input type="text" class="form-control" name="dosage_form" id="dosage_form" placeholder="Nhập dạng bào chế ở đây" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_specification">Đặc điểm kĩ thuật nhận dạng</label>
                                            <input type="text" class="form-control" name="product_specification" value="" id="product_specification" placeholder="Nhập đặc điểm kĩ thuật" required>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_target">Đối tượng sử dụng</label>
                                            <input type="text" class="form-control" name="product_target" value="" id="product_target" placeholder="Chỉ định và chống chỉ định" required>
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_desciption">Thông tin thêm</label>
                                            <textarea class="form-control" id="product_desciption" name="product_desciption" rows="5" cols="10"></textarea>
                                        </div>

                                        <div class="form-group mb-3 col-md-6">
                                            <label for="excipients">Thành phần</label> 
                                            <select class="form-control-sm" type="text" id="excipients" name="excipients" required>
                                            </select> <button class="btn-dropdown btn-success" type="button" onclick="addNewExcipient()">+</button><br>
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
                                            <input type="text" class="form-control" name="excipients_string"   value="" id="excipients_string" required readonly>
                                        </div>

                                        <div class="form-group  mb-3 col-md-6">
                                            <label>Nhà cung cấp</label><br/>
                                            <select class="form-control-sm" id="suplier_id" name="suplier_id" required>
                                                <c:forEach items="${suppliers}" var="supplier">
                                                    <option value="${supplier.supplierId}">${supplier.supplierName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group  mb-3 col-md-6">
                                            <label>Quốc gia</label><br/>
                                            <select class="form-control-sm" id="product_country_code" name="product_country_code" required>
                                            </select>
                                        </div>
                                        <div class="form-group  mb-3 col-md-6">
                                            <label>Thuốc kê theo đơn bác sĩ</label><br />
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="is_prescription" id="is_prescription_yes" value="0" checked />
                                                <label class="form-check-label" for="is_prescription_yes">Yes</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="is_prescription" id="is_prescription_no" value="1" />
                                                <label class="form-check-label" for="is_prescription_no">No</label>
                                            </div>
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="units">Đơn vị lưu trữ</label> 
                                            <select class="form-control-sm" type="text" id="units" name="units" required>
                                            </select> <button class="btn-dropdown btn-success" type="button" onclick="addNewUnit()">+</button><br>
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
                                            <input type="text" class="form-control" name="units_string"   value="" id="units_string" required readonly>
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="product_image">Avatar</label>
                                            <input type="file" class="form-control" id="product_image" accept="image/*" required>
                                            <input type="text" class="form-control" id="product_image_submit" style="" name="product_image_submit">
                                        </div>
                                        <div class="form-group mb-3 col-md-6">
                                            <label for="date_of_birth">Demo Img</label>
                                            <img id="avatarPreview" class="mt-3" src=""  alt="Avatar Preview" style="max-width: 300px; display: none;">
                                        </div>
                                        <p>${errorMessage}</p>
                                        <div class="form-group mb-3 col-md-12">
                                            <button type="submit" class="btn btn-success" style="width: 100px" onclick="submitForm()">Submit</button>
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
            <c:if test="${error!=null}">
            window.alert('${error}');
            </c:if>
            <c:if test="${success!=null}">
            window.alert('${success==true?"Tạo thành công":"Tạo thất bại"}');
            </c:if>
            function ExcipientDetail(ex_id, quantity, uni_measurement) {
                this.ex_id = ex_id;
                this.quantity = quantity;
                this.uni_measurement = uni_measurement;
            }
            function UnitDetail(unit_id, quantity_per_unit, can_be_sold, sell_price) {
                this.unit_id = unit_id;
                this.quantity_per_unit = quantity_per_unit;
                this.can_be_sold = can_be_sold;
                this.sell_price = sell_price;
            }
            var countNumber = 0;
            var countNumber_unit = 0
            var listExcipientAdded = [];
            var listUnitAdded = [];
            const excipientsJson = '${excipientsJson}';
            const excipientsData = JSON.parse(excipientsJson);
            const unitsJson = '${unitsJson}';
            const unitsData = JSON.parse(unitsJson);
            excipientsData.forEach(function (excipient) {
//                console.log(excipient)
                document.getElementById("excipients").innerHTML += `<option value="` + excipient.excipientId + `">` + excipient.excipientName + `</option>`;
            });
            unitsData.forEach(function (unit) {
//                console.log(excipient)
                document.getElementById("units").innerHTML += `<option value="` + unit.unitId + `">` + unit.unitName + `</option>`;
            });
            fetch('https://restcountries.com/v3.1/all')
                    .then(response => response.json())
                    .then(data => {
                        data.forEach(country => {
//                            console.log(`Country: `+country.name.common+`), Code:`+ country.cca2);
                            document.getElementById("product_country_code").innerHTML += createCountryOption(country.name.common, country.cca2);
                        });
                    })
                    .catch(error => console.error('Error:', error));

            function createCountryOption(country_name, country_code) {
                return `<option value="` + country_code + `">` + country_name + `(` + country_code + `)</option>`;
            }
            function addNewUnit() {
                var units_tag = document.getElementById("units");
                var unit_id = units_tag.value;
                var unit_name = units_tag.options[units_tag.selectedIndex].text;
                var newUnitDetail = new UnitDetail(unit_id, 1, false, 0);
                var check = true;
                listUnitAdded.forEach(function (unitDetail) {
                    if (units_tag.value === unitDetail.unit_id) {
                        check = false;
                    }
                });
                if (check) {
                    countNumber_unit++;
                    listUnitAdded.push(newUnitDetail);
                    rowContent = creatRowUnit(countNumber_unit, unit_id, unit_name);
                    var newRow = document.createElement('tr');
                    newRow.id = "rowIdUnit_" + countNumber_unit;
                    newRow.innerHTML = rowContent;
                    document.getElementById("units_in_drug").appendChild(newRow);
                }
                changeUnitsString();
            }
            function  addNewExcipient() {
                var excipients_tag = document.getElementById("excipients");
                var ex_id = excipients_tag.value;
                var ex_name = excipients_tag.options[excipients_tag.selectedIndex].text;
                var newExcipientDetail = new ExcipientDetail(ex_id, 0.01, 'gram');
                var check = true;
                listExcipientAdded.forEach(function (excipientDetail) {
                    if (excipients_tag.value === excipientDetail.ex_id) {
                        check = false;
                    }
                });
                if (check) {
                    countNumber++;
                    listExcipientAdded.push(newExcipientDetail);
                    rowContent = createRow(countNumber, ex_id, ex_name);
                    var newRow = document.createElement('tr');
                    newRow.id = "rowId_" + countNumber;
                    newRow.innerHTML = rowContent;
                    document.getElementById("exs_in_drug").appendChild(newRow);
                }
                changeExcipientsString();
            }
            function changeQuantityPerUnit(quantity_tag, unit_id) {
                listUnitAdded.forEach(function (unitDetail) {
                    if (unit_id == unitDetail.unit_id) {
                        if (quantity_tag.value !== '') {
                            unitDetail.quantity_per_unit = parseInt(quantity_tag.value);
                        } else {
                            unitDetail.quantity_per_unit = 1;
                        }
                    }
                });
                changeUnitsString();
            }

            function changeQuantity(quantity_tag, ex_id) {
                listExcipientAdded.forEach(function (excipientDetail) {
                    if (ex_id == excipientDetail.ex_id) {
                        if (quantity_tag.value !== '') {
                            excipientDetail.quantity = parseFloat(quantity_tag.value);
                        } else {
                            excipientDetail.quantity = 0.01;
                        }
                    }
                });
                changeExcipientsString();
            }
            function changePrice(price_tag, unit_id) {
                listUnitAdded.forEach(function (unitDetail) {
                    if (unit_id == unitDetail.unit_id) {
                        if (price_tag.value !== '') {
                            unitDetail.sell_price = parseFloat(price_tag.value);
                        } else {
                            unitDetail.sell_price = 0;
                        }
                    }
                });
                changeUnitsString();
            }

            function changeCanBeSold(sold_tag, unit_id) {
                listUnitAdded.forEach(function (unitDetail) {
                    if (unit_id == unitDetail.unit_id) {
                        if (sold_tag.value !== '') {
                            unitDetail.can_be_sold = sold_tag.value == 'yes';
                        } else {
                            unitDetail.sell_price = 0;
                        }
                    }
                });
                changeUnitsString();
            }
            function changeUnitMeasurement(measurement_tag, ex_id) {
                listExcipientAdded.forEach(function (excipientDetail) {
                    if (ex_id == excipientDetail.ex_id) {
                        if (measurement_tag.value !== '') {
                            excipientDetail.uni_measurement = measurement_tag.value;
                        } else {
                            excipientDetail.uni_measurement = 'gram';
                        }

                    }
                });
                changeExcipientsString();
            }

            function changeUnitsString() {
                document.getElementById("units_string").value = "";
                var valueOfArrString = "";
                listUnitAdded.forEach(function (unitDetail) {
                    valueOfArrString += (unitDetail.unit_id + "_" + unitDetail.quantity_per_unit + "_" + unitDetail.can_be_sold + "_" + unitDetail.sell_price + ",");
                });
                document.getElementById("units_string").value = valueOfArrString.slice(0, -1);
            }
            function changeExcipientsString() {
                document.getElementById("excipients_string").value = "";
                var valueOfArrString = '';
                listExcipientAdded.forEach(function (excipientDetail) {
                    valueOfArrString += (excipientDetail.ex_id + "_" + excipientDetail.quantity + "_" + excipientDetail.uni_measurement + ",");
                });
                document.getElementById("excipients_string").value = valueOfArrString.slice(0, -1);
            }
            function creatRowUnit(count_num, unit_id, unit_name) {
                rowContent = `
                                                    <td>
                                                        <input class="form-control-sm" type="text" id="unit_name_` + count_num + `" name="unit_name_` + count_num + `" value="` + unit_name + `" readonly required>
                                                        <input class="form-control-sm" type="text" id="unit_id_` + count_num + `" name="unit_id_` + count_num + `" value="` + unit_id + `" readonly hidden>
                                                    </td>
                                                    <td>
                                                        <input onchange="changeQuantityPerUnit(this,` + unit_id + `)" type="number" class="form-control-sm" min="1" step="1" value="1" required>
                                                    </td>
                                                    <td>
                                                        <select onchange="changeCanBeSold(this,` + unit_id + `)" class="form-control-sm" required>
                                                            <option value="yes">yes</option>
                                                            <option value="no" selected>no</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input onchange="changePrice(this,` + unit_id + `)" type="number" class="form-control-sm" min="0" step="0.01" value="0" required placeholder = "VNĐ">
                                                    </td>
                                                    <td><button type="button" class="btn-close" value="` + count_num + `" onclick="deleteRowUnit(` + count_num + `,` + unit_id + `)"></button></td>
                                                `;
                return rowContent;
            }

            function createRow(count_num, ex_id, ex_name) {
                rowContent = `<tr id="rowId_` + count_num + `">
                                                    <td>
                                                        <input class="form-control-sm" type="text" id="excipient_name_` + count_num + `" name="excipient_name_` + count_num + `" value="` + ex_name + `" readonly required>
                                                        <input class="form-control-sm" type="text" id="excipient_id_` + count_num + `" name="excipient_id_` + count_num + `" value="` + ex_id + `" readonly hidden>
                                                    </td>
                                                    <td>
                                                        <input onchange="changeQuantity(this,` + ex_id + `)" type="number" class="form-control-sm" min="0.01" step="0.01" value="0.01" required>
                                                    </td>
                                                    <td>
                                                        <select onchange="changeUnitMeasurement(this,` + ex_id + `)" class="form-control-sm" required>
                                                            <option value="gram">gram</option>
                                                            <option value="ml">ml</option>
                                                        </select>
                                                    </td>
                                                    <td><button type="button" class="btn-close" value="` + count_num + `" onclick="deleteRow(` + count_num + `,` + ex_id + `)"></button></td>
                                                </tr>`;
                return rowContent;
            }
            function deleteRowUnit(count_num, unit_id) {
                var arrLenght = listUnitAdded.length;
                for (i = arrLenght - 1; i >= 0; i--) {
                    if (listUnitAdded[i].unit_id == unit_id) {
                        listUnitAdded.splice(i, 1);
                    }
                }
                var findingElement = document.getElementById('rowIdUnit_' + count_num);
                if (findingElement) {
                    findingElement.remove();
                }
                changeUnitsString();
            }
            function deleteRow(count_num, ex_id) {
                var arrLenght = listExcipientAdded.length;
                for (i = arrLenght - 1; i >= 0; i--) {
                    if (listExcipientAdded[i].ex_id == ex_id) {
                        listExcipientAdded.splice(i, 1);
                    }
                }
                var findingElement = document.getElementById('rowId_' + count_num);
                if (findingElement) {
                    findingElement.remove();
                }
                changeExcipientsString();
            }

            // Validate date of birth to ensure it's not greater than today

            // Validate the avatar to check if it's an image and smaller than 5MB
            function validateImage(file) {
                return true;
            }

            // Form validation
            function submitForm() {
                document.getElementById("createProductForm").submit();
            }

            // Avatar preview and encoding to base64
            document.getElementById('product_image').addEventListener('change', function (event) {
                const file = event.target.files[0];
                if (!validateAvatar(file)) {
                    return;
                } else {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const base64Image = e.target.result;
                        document.getElementById('avatarPreview').src = base64Image;
                        document.getElementById('avatarPreview').style.display = 'block';
                        document.getElementById('product_image_submit').value = base64Image;
                    };
                    reader.readAsDataURL(file);
                }
            });
            function validateAvatar(file) {
                const validImageTypes = ['image/jpeg','image/gif'];
                const maxFileSize = 5 * 1024 * 1024; // 5MB
                var check = true;
                if (!validImageTypes.includes(file.type)) {
                    windows.alert("Please upload a valid image (JPG, GIF).");
                    check = false;
                }

                if (file.size > maxFileSize) {
                    windows.alert("Avatar file size must be less than 5MB.");
                    check = false;
                }

                return check;
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
            }
            );
        </script>

    </body>
</html>
