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
                                            <div style="margin: 50px;">
                                                <!-- Thẻ select -->
                                                <select class="searchable-select" style="width: 300px;" id="product_selection">
                                                </select>
                                                <button onclick="viewProductDetailAction()" id="addImportOrderDetailBtn" class="btn btn-success" data-toggle="modal" data-target="#myModal">Xem chi tiết</button>
                                            </div>
                                            <div>
                                                <form action="CreateImportOrder" method="post" class="form" style="width: 100%" id="formSubmitListOrderDetail">
                                                    <input type="text" name="product_list_str" id="product_list_str" readonly="" required="" hidden="">
                                                    <label for="expected_date">Ngày nhập dự kiến*</label>&nbsp&nbsp&nbsp<label for="import_order_code">Mã yêu cầu nhập*</label><br>
                                                    <input class="form-control-sm" type="date" name="expected_date" required="" id="expected_date">
                                                    <input class="form-control-sm" type="text" name="import_order_code" required="" id="import_order_code">
                                                    <table class="table" id="import_order_table">
                                                        <h2>Danh sách sản phẩm được thêm</h2>
                                                        <tr>
                                                            <th>Mã sp</th>
                                                            <th>Số lô</th>
                                                            <th>Tên sp</th>
                                                            <th>Ảnh</th>
                                                            <th>Đơn vị</th>
                                                            <th style="color: red">Số lượng*</th>                                                        
                                                            <th style="color: red">Giá đơn vị*</th>                                                        
                                                            <th>Tổng giá</th>                                                        
                                                            <th>Xóa</th>                                                        
                                                        </tr>
                                                    </table>

                                                    <div style="display: flex;flex-direction: row;justify-content: end"><div>Tổng giá trị đơn hàng:&nbsp&nbsp</div> <div><span id="total_price_order_list">0</span>&nbspVND</div></div>
                                                    <div style="display: flex;flex-direction: row;justify-content: space-between">
                                                        <button type="button" onclick="backToMain()" class="btn btn-danger">Thoát</button>
                                                        <button type="button" onclick="submitCreateForm()" class="btn btn-success">Xác nhận tạo</button>
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
                                <h4 class="modal-title">Chi tiết sản phẩm</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal Body -->
                            <div class="modal-body">
                                                    <table class="table">
                                                        <tr>
                                                            <td>Mã đăng kí</td>
                                                            <td id="modal_product_code">HE171764</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Tên sp</td>
                                                            <td id="modal_product_name">Cell2</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Danh mục</td>
                                                            <td id="modal_product_cate">Cell2</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Nhà sản xuất</td>
                                                            <td id="modal_product_sup">Cell2</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Quốc gia</td>
                                                            <td id="modal_product_country">Cell2</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Giá bán</td>
                                                            <td id="modal_product_price">
                                                                
                                                            </td>
                                                        </tr>
                                                    </table>
                            </div>

                            <!-- Modal Footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success" data-dismiss="modal" onclick="triggerAdd()">Thêm</button>
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
            var product_list_submit = [];
            const products = JSON.parse('${productsJson}');
            console.log(products);
            products.forEach(product => {
                var newRow = document.createElement('option');
                var rowContent = truncateString(product.productName)+"("+product.productCode+")";
                newRow.id = 'product_row_' + product.productId;
                newRow.value = product.productId+"";
                newRow.innerHTML = rowContent;
                document.getElementById("product_selection").appendChild(newRow);
            });
                    <c:if test="${error!=null}">
                        window.alert("${error}");
                    </c:if>
            function triggerAdd(){
                addInOrderList(parseInt(document.getElementById("product_selection").value));
            }
            function addInOrderList(product_id) {
                var checkNotExist = true;
                product_list_submit.forEach(product_id_finding =>{
                    if(product_id_finding == product_id){
                        checkNotExist = false;
                    }
                });
                if(checkNotExist){
                    var product = findProduct(product_id);
                    if (product != null) {
                        product_list_submit.push(product.productId);
                        var newRow = document.createElement('tr');
                        newRow.id = 'product_detail_row_'+product.productId;
                        var listSelectionUnit = ``;
                        var productUnitLe = product.productUnits.length;
                        for(i=0;i<productUnitLe;i++){
                            
                            listSelectionUnit += `<option value="`+product.productUnits[i].unit.unitId+`">`+product.productUnits[i].unit.unitName+`</option>`;
                        }
                        var selectionUnits = `<select name="unit_for_product_`+product_id+`" id="unit_for_product_`+product_id+`" class="form-control-sm">`+listSelectionUnit+`</select>`
                        var rowContent = `                  <td>` + product.productCode + `</td>
                                                            <td><input class="form-control-sm" value="" type="text" id="batch_code_`+product.productId+`" name="batch_code_`+product.productId+`" required=""></td>
                                                            <td>` + truncateString(product.productName) + `</td>
                                                            <td><img style="height: 60px;width: auto class="img-fluid" src="` + (product.productImages.length >= 1 ? product.productImages[0].imageBase64 : "") + `" alt="alt"/></td>
                                                            <td>`+selectionUnits+`</td>
                                                            <td><input required value="0" onchange="changeProductPrice(`+product.productId+`)" class="form-control-sm" type="number" min="1" id="quantity_`+product.productId+`" name="quantity_`+product.productId+`"></td>
                                                            <td><input required value="0" id="import_price_`+product.productId+`" onchange="changeProductPrice(`+product.productId+`)" step="0.01"  class="form-control-sm" type="number" min="0.01" name="import_price_`+product.productId+`"> VND</td>
                                                            <td><span id="total_price_row_`+product.productId+`" name="total_price_row_`+product.productId+`">0</span> VND</td>
                                                            <td><button type="button" class="btn-sm btn-close" onclick="removeProduct(`+product.productId+`)"></button></td>`;
                        newRow.innerHTML = rowContent;
                        document.getElementById("import_order_table").appendChild(newRow);
                        document.getElementById("product_list_str").value = arrayToString(product_list_submit);
                    }
                } else{
                    document.getElementById("product_list_str").value = arrayToString(product_list_submit);
                    window.alert("Sản phẩm đã tồn tại trong danh sách");
                }
                
            }
            function removeProduct(product_id){
                var newArr = deleteElementArrByaValue(product_list_submit,product_id);
                product_list_submit = newArr;
                document.getElementById("product_list_str").value = arrayToString(product_list_submit);
                let removeE = document.getElementById('product_detail_row_'+product_id);
                if(removeE){
                    removeE.remove();
                }
                getFullPrice();
            }
            function changeProductPrice(product_id){
                var priceString = document.getElementById("import_price_"+product_id).value;
                var quantityString = document.getElementById("quantity_"+product_id).value;
                if(quantityString==null||quantityString==''){
                    document.getElementById("quantity_"+product_id).value = "0";
                }
                if(priceString==null||priceString==''){
                    document.getElementById("import_price_"+product_id).value = "0";
                }
                var pricePerUnit = parseFloat(document.getElementById("import_price_"+product_id).value);
                var quantity= parseInt(document.getElementById("quantity_"+product_id).value);
                if(pricePerUnit<0){
                    document.getElementById("import_price_"+product_id).value = "0";
                }
                if(quantity<0){
                    document.getElementById("quantity_"+product_id).value = "0";
                }
                pricePerUnit = parseFloat(document.getElementById("import_price_"+product_id).value);
                quantity= parseInt(document.getElementById("quantity_"+product_id).value);
                document.getElementById("total_price_row_"+product_id).innerHTML = pricePerUnit*quantity;
                getFullPrice();
            }
            function viewProductDetailAction(){
                var pidStr = document.getElementById("product_selection").value;
                var arrLe = products.length;
                for(i=0;i<arrLe;i++){
                   if(pidStr==products[i].productId){
                       document.getElementById("modal_product_code").innerHTML = products[i].productCode;
                       document.getElementById("modal_product_name").innerHTML = products[i].productName;
                       document.getElementById("modal_product_cate").innerHTML = products[i].productCategory.productCategoryName;
                       document.getElementById("modal_product_sup").innerHTML = products[i].supplier.supplierName;
                       document.getElementById("modal_product_country").innerHTML = products[i].productCountryCode;
                       var arrULe = products[i].productUnits.length;
                       document.getElementById("modal_product_price").innerHTML = '';
                       for(j=0;j<arrULe;j++){
                           var contentRow = `<tr>
                                                    <td>`+products[i].productUnits[j].unit.unitName+`:</td>
                                                    <td>`+products[i].productUnits[j].sellPrice+`</td>
                                            </tr><br>`;
                           document.getElementById("modal_product_price").innerHTML +=contentRow;
                       }
                   }
                }
            }
            function getFullPrice(){
                var arrLe = product_list_submit.length;
                var fullPrice = 0;
                for(i=0;i<arrLe;i++){
                    var tempPrice = parseFloat(document.getElementById("total_price_row_"+product_list_submit[i]).innerHTML);
                    fullPrice += tempPrice;
                }
                document.getElementById("total_price_order_list").innerHTML = fullPrice;
                return fullPrice;
            }
            function backToMain(){
                document.getElementById("backToMainForm").submit();
            }
            function submitCreateForm(){
                var check = true;
                
                if(document.getElementById("product_list_str").value==''){
                    check = false;
                    window.alert("Không được tạo danh sách rỗng");
                }
                var arrLe = product_list_submit.length;
                for(i=0;i<arrLe;i++){
                    var quant = parseInt(document.getElementById("quantity_"+product_list_submit[i]).value);
                    var pricePer = parseFloat(document.getElementById("import_price_"+product_list_submit[i]).value);
                    console.log("batch_code_"+product_list_submit[i]);
                    var batch = document.getElementById("batch_code_"+product_list_submit[i]).value;
                    if(quant<=0||pricePer<=0){
                        check = false;
                        window.alert("Giá trị của một đơn hàng phải lớn hơn 0");
                        break;
                    }
                    if(batch.replace(/\s+/g, '')==''){
                            check = false;
                            window.alert("Mã lô không được để trống");
                            break;
                        }    
                }
                if(document.getElementById("import_order_code").value.replace(/\s+/g, '')==''){
                    check = false;
                    window.alert("Mã nhập hàng không được để trống");
                }
                if(!isDateAfterThreeDays()){
                    check = false;
                    window.alert("Ngày nhập dự kiến phải sau ngày hiện tại");
                }
                if(check == true){
                    document.getElementById("formSubmitListOrderDetail").submit();
                }
            }
            function findProduct(product_id) {
                var listLength = products.length;
                console.log(product_id);
                for (i = 0; i < listLength; i++) {
                    if (products[i].productId === product_id) {
                        return products[i];
                        break;
                    }                    
                }
                return null;
            }
            
            function truncateString(str) {
                if (str.length > 20) {
                    return str.substring(0, 20) + '...';
                }
                return str;
            }
            function arrayToString(arr) {
                return arr.join(',');
            }
            function deleteElementArrByaValue(arr,input_value){
                let newArr = [];
                for (let i = 0; i < arr.length; i++) {
                    if (arr[i] !=input_value) {
                        newArr.push(arr[i]);
                    }
                }
                return newArr;
            }
            function isDateAfterThreeDays(){
                var inputDate = new Date(document.getElementById("expected_date").value);
                var today = new Date();
                var threeDayLater = new Date(today.setDate(today.getDate()));
                if(inputDate>threeDayLater){
                    return true;
                }
                return false;
            }
        </script>

    </body>
</html>
