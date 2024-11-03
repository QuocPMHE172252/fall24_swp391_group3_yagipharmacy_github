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
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
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
                                        <span style="color: red">Lưu ý không nhập sản phẩm trùng lặp*</span>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive" style="display: flex;flex-direction: column;">
                                            <div>
                                                <form action="#" method="post" class="form" style="width: 100%" id="formSubmitListOrderDetail">
                                                    <input type="text" name="product_list_str" id="product_list_str" readonly="" required="" hidden="">
                                                    <label for="expected_date">Ngày nhập dự kiến: &nbsp;</label><span name="expected_date" id="expected_date"></span><br>
                                                    <label for="import_order_code">Mã đơn nhập: &nbsp;</label><span name="import_order_code" id="import_order_code"></span>
                                                    <table class="table" id="import_order_table">
                                                        <h2>Danh sách đơn nhập</h2>
                                                        <tr>
                                                            <th>Mã sp</th>
                                                            <th>Tên sp</th>
                                                            <th>Số lô</th>
                                                            <th>Ảnh</th>
                                                            <th>Đơn vị</th>
                                                            <th>Số lượng</th>                                                        
                                                            <th>Nhà cung cấp</th>
                                                            <th>Giá nhập</th>
                                                            <th>Trạng thái hàng</th>
                                                            <th>Cập nhật</th>                                                        
                                                        </tr>
                                                    </table>
                                                    <div style="display: flex;flex-direction: row;justify-content: space-between">
                                                        <button type="button" onclick="backToMain()" class="btn btn-danger">Thoát</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <form action="ImportOrderList" id="backToMainForm"></form>

                <!-- The Modal -->
                <div class="modal" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Cập nhật đơn nhập</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal Body -->
                            <div class="modal-body">
                                <form action="UpdateImportOrder" method="post" id="updateForm">
                                    <input type="text" name="submit_import_detail_id" id="submit_import_detail_id" hidden="">
                                    <input type="text" name="submit_import_id" id="submit_import_id" hidden="">
                                    <table class="table">
                                                        <tr>
                                                            <td>Trạng thái</td>
                                                            <td><select type="text" id="submit_processing" name="submit_processing" onchange="checkPRO()">
                                                                    <option value="0">Chờ xác nhận</option>
                                                                    <option value="1">Đã từ chối</option>
                                                                    <option value="2">Đang giao hàng</option>
                                                                    <option value="3">Đã nhận</option>
                                                                    <option value="4">Đã kiểm định</option>
                                                                </select></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Số lô</td>
                                                            <td><input type="text" id="submit_batch_code" name="submit_batch_code" readonly=""></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Giá nhập</td>
                                                            <td><input type="number" min="0" step="0.1" id="submit_import_price" name="submit_import_price" readonly></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Ngày nhập</td>
                                                            <td><input type="date" min="0" id="submit_import_date" name="submit_import_date" readonly></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Ngày sản xuất</td>
                                                            <td><input type="date" min="0" id="submit_MFG_date" name="submit_MFG_date" readonly></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Ngày hết hạn</td>
                                                            <td><input type="date" min="0" id="submit_EXP_date" name="submit_EXP_date" readonly></td>
                                                        </tr>
                                    </table> 
                                </form>
                                                    
                            </div>

                            <!-- Modal Footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success" data-dismiss="modal" onclick="triggerUpdate()">Cập nhật</button>
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
                    <form action="ImportOrderList" method="get" id="backMainForm"></form>
                    

                    

        </div>
        <!--   Core JS Files   -->
        <script src="./assets/js/core/jquery-3.7.1.min.js"></script>
        <script src="./assets/js/core/popper.min.js"></script>
        <script src="./assets/js/core/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 

        
        <!-- Bootstrap Notify -->
        <!--<script src="./assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>-->

        <!-- jQuery Vector Maps -->
        
        <!-- Thư viện JS jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Thư viện JS Select2 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <!-- Khởi tạo Select2 -->
    <script>
        $(document).ready(function() {
            $('.searchable-select').select2({
                placeholder: "Select an option",
                allowClear: true
            });
        });
    </script>
    <script>
        const data_import_orders = JSON.parse('${dataImportOrder}');
        console.log(data_import_orders);
        document.getElementById('expected_date').innerHTML = data_import_orders.importExpectedDate;
        document.getElementById('import_order_code').innerHTML = data_import_orders.importOrderCode;
        document.getElementById('submit_import_id').value = ''+data_import_orders.importOrderId;
        data_import_orders.importOrderDetails.forEach(importOrderDetail =>{
            var processOfImp = '';
            var processNum  = importOrderDetail.processing;
            console.log(processNum);
            if(processNum==null||processNum==0){
                processOfImp = 'Chờ xác nhận';
            } else if(processNum == 1){
                processOfImp = 'Đã từ chối';
            } else if(processNum == 2){
                processOfImp = 'Đang giao hàng';
            } else if(processNum == 3){
                processOfImp = 'Đã nhận';
            } else if(processNum == 4){
                processOfImp = 'Đã kiểm định';
            } else {
                processOfImp = 'Không xác định';
            }
            var imp_row = `<tr>
                                <td>`+importOrderDetail.product.productCode+`</td>
                                <td>`+importOrderDetail.product.productName+`</td>
                                <td>`+importOrderDetail.batchCode+`</td>
                                <td><img style="height:60px;width:auto" class="img-fluid" src="`+importOrderDetail.product.productImages[0].imageBase64+`" alt="Ảnh sản phẩm"/></td>
                                <td>`+importOrderDetail.unit.unitName+`</td>
                                <td>`+importOrderDetail.quantity+`</td>
                                <td>`+importOrderDetail.supplier.supplierName+`</td>
                                <td>`+importOrderDetail.importPrice+`</td>
                                <td>`+processOfImp+`</td>
                                <td><button `+(importOrderDetail.processing==1||importOrderDetail.processing==4?'disabled class="btn btn-black"':'class="btn btn-success" ')+` type="button" onclick="viewImportDetailAction(`+importOrderDetail.importOrderDetailId+`)" id="addImportOrderDetailBtn" data-toggle="modal" data-target="#myModal">Cập nhật</button></td>
                           </tr>`;
            document.getElementById("import_order_table").innerHTML += imp_row;
        });
        function viewImportDetailAction(inp_id){
            data_import_orders.importOrderDetails.forEach(importOrderDetail =>{
                if(importOrderDetail.importOrderDetailId == inp_id){
                    document.getElementById('submit_import_detail_id').value = importOrderDetail.importOrderDetailId;
                    document.getElementById('submit_processing').value = importOrderDetail.processing;
                    document.getElementById('submit_batch_code').value = importOrderDetail.batchCode;
                    document.getElementById('submit_import_price').value = importOrderDetail.importPrice;
                    let dateObject = new Date(importOrderDetail.importDate);
                    let formattedDate = dateObject.toISOString().split('T')[0];
                    document.getElementById('submit_import_date').value = formattedDate;
                    if(importOrderDetail.processing==1||importOrderDetail.processing==4){
                        document.getElementById('submit_processing').disabled = true;
                    }
                }
            });
        }
        function checkPRO(){
            var valueOfPro = document.getElementById("submit_processing").value;
            if(valueOfPro=="4"){
                console.log("Di qua day");
                document.getElementById("submit_batch_code").removeAttribute("readonly");
                document.getElementById("submit_import_date").removeAttribute("readonly");
                document.getElementById("submit_MFG_date").removeAttribute("readonly");
                document.getElementById("submit_EXP_date").removeAttribute("readonly");
                document.getElementById("submit_import_price").removeAttribute("readonly");
            } else {
                document.getElementById("submit_batch_code").setAttribute("readonly",true);
                document.getElementById("submit_batch_code").value = '';
                document.getElementById("submit_import_date").readonly = true;
                document.getElementById("submit_import_date").value = '1970-01-01';
                document.getElementById("submit_MFG_date").setAttribute("readonly",true);
                document.getElementById("submit_MFG_date").value = '';
                document.getElementById("submit_EXP_date").setAttribute("readonly",true);
                document.getElementById("submit_EXP_date").value = '';
                document.getElementById("submit_import_price").setAttribute("readonly",true);
                document.getElementById("submit_import_price").value = '';
            }
        }
        function triggerUpdate(){
            document.getElementById('updateForm').submit();
        }
        function backToMain(){
            document.getElementById('backMainForm').submit();
        }
    </script>