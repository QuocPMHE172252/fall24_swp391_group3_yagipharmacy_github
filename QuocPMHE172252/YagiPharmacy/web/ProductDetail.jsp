<%-- 
    Document   : ProducDetail
    Created on : Oct 15, 2024, 9:40:20 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi" class="h-100">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Sản phẩm | Chi tiết sản phẩm</title>

        <link rel="shortcut icon" type="image/x-icon" href="https://freevector-images.s3.amazonaws.com/uploads/vector/preview/36682/36682.png" />
        <link rel="stylesheet" href="./css/main.css">
        <link rel="stylesheet" href="./css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/bootstrap.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <!-- Custom css - Các file css do chúng ta tự viết -->
        <link rel="stylesheet" href="assets/css/product-detail.css" type="text/css">
    </head>

    <body>
        <!-- header -->
         <jsp:include page="./layout/header.jsp"/>
        <!-- end header -->

        <main role="main">
            <!-- Block content - Đục lỗ trên giao diện bố cục chung, đặt tên là `content` -->
            <div class="container mt-4">
                <div id="thongbao" class="alert alert-danger d-none face" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>

                <div class="card">
                    <div class="container-fliud">
                        <form name="frmsanphamchitiet" id="frmsanphamchitiet" method="post"
                              action="/php/twig/frontend/giohang/themvaogiohang">
                            <input type="hidden" name="sp_ma" id="sp_ma" value="5">
                            <input type="hidden" name="sp_ten" id="sp_ten" value="Samsung Galaxy Tab 10.1 3G 16G">
                            <input type="hidden" name="sp_gia" id="sp_gia" value="10990000.00">
                            <input type="hidden" name="hinhdaidien" id="hinhdaidien" value="samsung-galaxy-tab-10.jpg">

                            <div class="wrapper row">
                                <div class="preview col-md-6">
                                    <div class="preview-pic tab-content">
                                        <%int count = 0;%>
                                        <c:forEach items="${product.productImages}" var="p_img">
                                            <%count++;%>
                                            <div class="tab-pane active" id="pic-<%=count%>">
                                                <img src='${p_img.imageBase64}'>
                                            </div>
                                        </c:forEach>
                                        <%count = 0;%>
                                    </div>
                                    <ul class="preview-thumbnail nav nav-tabs">
                                        <c:forEach items="${product.productImages}" var="p_img">
                                            <%count++;%>
                                            <li class="<%=(count==1?"active":"")%>">
                                                <a data-target="#pic-<%=count%>" data-toggle="tab" class="">
                                                    <img src='${p_img.imageBase64}'>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="details col-md-6">
                                    <h3 class="product-title">${product.productName}</h3>
                                    <div class="rating">
                                        <div class="stars">
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                        <span class="review-no">999 reviews</span>
                                    </div>
                                    <h4 class="price" style="text-decoration-line:none;" id="price_area">Giá/: <span id="price_location"></span></h4>
                                    <h5 class="">Chọn đơn vị tính:
                                        <c:forEach items="${product.productUnits}" var="p_unit">
                                            <c:if test="${p_unit.canBeSold}">
                                                <button type="button" class="btn-info btn btn-lg" value="" onclick="changeUnit(${p_unit.productUnitId})">${p_unit.unit.unitName}</button>
                                            </c:if>
                                            
                                        </c:forEach>
                                    </h5>
                                    <h5 class="">Danh mục:  ${product.productCategory.productCategoryName}</h5></br>
                                    <h5 class="">Quy cách:  ${product.productSpecification}</h5></br>
                                    <h5 class="">Xuất xứ thương hiệu:  ${product.productCountryCode}</h5></br>
                                    <h5 class="">Nhà sản xuất:  ${product.brand}</h5></br>

                                    <%count = 0;%>
                                    <h5 class="">Thành phần:  <c:forEach items="${product.productExcipients}" var="p_ex">
                                            <%count++;%>
                                            ${p_ex.excipient.excipientName}, 
                                        </c:forEach></h5></br>
                                    <h5 class="">Mô tả ngắn:  ${product.productDescription}</h5><br>
                                    <h5 class="">Số đăng kí:  ${product.productCode}</h5><br>
                                    <p class="vote"><strong>100%</strong> hàng <strong>Chất lượng</strong>, đảm bảo
                                        <strong>Uy
                                            tín</strong>!</p>

                                    <div class="form-group">
                                        <label for="soluong">Số lượng đặt mua:</label>
                                        <input onchange="checkQuan()" type="number" value="1" min="1" max="999" onkeyup="checkValueOfQuan()" class="form-control-sm" id="soluong" name="soluong">
                                    </div>
                                    <div class="action">
                                        <button type="button" onclick="addToCart()" class="add-to-cart btn btn-default" id="btnThemVaoGioHang">Thêm vào giỏ hàng</button>
                                    </div>
                                </div>

                            </div>
                        </form>
                        <form id="cartViewForm" action="ViewCart" method="get">
                            <input type="text" id="cart_string_submit" name="cart_string_submit" value="" hidden="">
                        </form>
                    </div>
                </div>

                <div class="card">
                    <div class="container-fluid">
                        <h3>Thông tin chi tiết về Sản phẩm</h3>
                        <div class="row">
                            <div class="col">
                                ${product.productLongDesciption}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End block content -->
        </main>

        <!-- footer -->
        <jsp:include page="./layout/footer.jsp"/>
        <!-- end footer -->

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/popperjs/popper.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Custom script - Các file js do mình tự viết -->
        <script src="assets/js/app.js"></script>

    </body>
    <script>

                    const productJson = JSON.parse(`${productJson}`);
                    console.log(productJson);
                    var unit_id_submit = 0;
                    var product_id_submit = productJson.productId;
                    var quantity_submit = 1;
                    productJson.productUnits.forEach(pUnit => {
                        if (true) {
                            unit_id_submit = pUnit.unit.unitId;
                            document.getElementById("price_area").innerHTML = `Giá/` + pUnit.unit.unitName + `: <span id="price_location">` + pUnit.sellPrice.toLocaleString('en-US') + ` vnđ</span>`
                        }
                    });
                    function changeUnit(p_unit_id) {
                        productJson.productUnits.forEach(pUnit => {
                            if (p_unit_id == pUnit.productUnitId) {
                                unit_id_submit = pUnit.unit.unitId;
                                document.getElementById("price_area").innerHTML = `Giá/` + pUnit.unit.unitName + `: <span id="price_location">` + pUnit.sellPrice.toLocaleString('en-US') + ` vnđ</span>`
                            }
                        });
                    }
                    function checkQuan() {
                        var quanStr = document.getElementById("soluong").value;
                        if (quanStr == '') {
                            quanStr = '0';
                        }
                        var quanInt = parseInt(quanStr);
                        if (quanInt <= 0) {
                            quanInt = 1;
                        }
                        quantity_submit = quanInt;
                        document.getElementById("soluong").value = quanInt + '';
                    }
                    function checkValueOfQuan(){
                        var quan = parseInt(document.getElementById("soluong").value);
                        if(quan>999){
                            document.getElementById("soluong").value = 999;
                        }
                    }
                    function addToCart() {
                        var checkNew = true;
                        var cart = JSON.parse(getCookie("cart"));
                        if (cart == null) {
                            var arrCart = [];
                            var stringOrderDetail = product_id_submit + "_" + unit_id_submit + "_" + quantity_submit;
                            arrCart.push(stringOrderDetail);
                            let jsonArr = JSON.stringify(arrCart);
                            setCookie('cart', jsonArr);
                        } else {
                            var stringOrderDetail = product_id_submit + "_" + unit_id_submit + "_" + quantity_submit;
                            let lCart = cart.length;
                            let checkEx = false;
                            for (i = 0; i < lCart; i++) {
                                let arrDetail = cart[i].split("_");
                                if (arrDetail[0] == product_id_submit) {
                                    cart[i] = stringOrderDetail;
                                    checkEx = true;
                                }
                            }
                            if (!checkEx) {
                                cart.push(stringOrderDetail);
                                let jsonArr = JSON.stringify(cart);
                                setCookie('cart', jsonArr);
                            } else {
                                let jsonArr = JSON.stringify(cart);
                                setCookie('cart', jsonArr);
                                checkNew = false;
                                window.alert("Đã thay đổi thông tin mua của sản phẩm");
                            }

                        }
                        console.log(getCookie('cart'));
                        if(checkNew){
                            window.alert("Thêm thành công vào giỏ hàng");
                        }
                    }
                    function setCookie(name, value, days) {
                        let now = new Date();
                        now.setTime(now.getTime() + (days * 24 * 60 * 60 * 1000));
                        let expires = "expires=" + now.toUTCString();
                        document.cookie = name + "=" + encodeURIComponent(value) + "; " + expires + "; path=/";
                    }

                    function getCookie(name) {
                        let cookieArr = document.cookie.split(";");
                        for (let i = 0; i < cookieArr.length; i++) {
                            let cookiePair = cookieArr[i].split("=");
                            if (name == cookiePair[0].trim()) {
                                return decodeURIComponent(cookiePair[1]);
                            }
                        }
                        return null
                    }
                    function arrayToString(arr) {
                        return arr.join(','); // Kết hợp các phần tử của mảng thành chuỗi, ngăn cách bởi dấu phẩy
                    }
    </script>
</html>
