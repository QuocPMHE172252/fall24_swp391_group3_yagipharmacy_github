<%-- 
    Document   : ViewCart
    Created on : Oct 16, 2024, 2:40:10 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <style>
            body{
                background: #ddd;
                min-height: 100vh;
                vertical-align: middle;
                display: flex;
                font-family: sans-serif;
                font-size: 0.8rem;
                font-weight: bold;
            }
            .title{
                margin-bottom: 5vh;
            }
            .card{
                margin: auto;
                max-width: 950px;
                width: 90%;
                box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                border-radius: 1rem;
                border: transparent;
            }
            @media(max-width:767px){
                .card{
                    margin: 3vh auto;
                }
            }
            .cart{
                background-color: #fff;
                padding: 4vh 5vh;
                border-bottom-left-radius: 1rem;
                border-top-left-radius: 1rem;
            }
            @media(max-width:767px){
                .cart{
                    padding: 4vh;
                    border-bottom-left-radius: unset;
                    border-top-right-radius: 1rem;
                }
            }
            .summary{
                background-color: #ddd;
                border-top-right-radius: 1rem;
                border-bottom-right-radius: 1rem;
                padding: 4vh;
                color: rgb(65, 65, 65);
            }
            @media(max-width:767px){
                .summary{
                    border-top-right-radius: unset;
                    border-bottom-left-radius: 1rem;
                }
            }
            .summary .col-2{
                padding: 0;
            }
            .summary .col-10
            {
                padding: 0;
            }
            .row{
                margin: 0;
            }
            .title b{
                font-size: 1.5rem;
            }
            .main{
                margin: 0;
                padding: 2vh 0;
                width: 100%;
            }
            .col-2, .col{
                padding: 0 1vh;
            }
            a{
                padding: 0 1vh;
            }
            .close{
                margin-left: auto;
                font-size: 0.7rem;
            }
            img{
                width: 3.5rem;
            }
            .back-to-shop{
                margin-top: 4.5rem;
            }
            h5{
                margin-top: 4vh;
            }
            hr{
                margin-top: 1.25rem;
            }
            form{
                padding: 2vh 0;
            }
            select{
                border: 1px solid rgba(0, 0, 0, 0.137);
                padding: 1.5vh 1vh;
                margin-bottom: 4vh;
                outline: none;
                width: 100%;
                background-color: rgb(247, 247, 247);
            }
            input{
                border: 1px solid rgba(0, 0, 0, 0.137);
                padding: 1vh;
                margin-bottom: 4vh;
                outline: none;
                width: 100%;
                background-color: rgb(247, 247, 247);
            }
            input:focus::-webkit-input-placeholder
            {
                color:transparent;
            }
            .btn{
                background-color: #000;
                border-color: #000;
                color: white;
                width: 100%;
                font-size: 0.7rem;
                margin-top: 4vh;
                padding: 1vh;
                border-radius: 0;
            }
            .btn:focus{
                box-shadow: none;
                outline: none;
                box-shadow: none;
                color: white;
                -webkit-box-shadow: none;
                -webkit-user-select: none;
                transition: none;
            }
            .btn:hover{
                color: white;
            }
            a{
                color: black;
            }
            a:hover{
                color: black;
                text-decoration: none;
            }
            #code{
                background-image: linear-gradient(to left, rgba(255, 255, 255, 0.253) , rgba(255, 255, 255, 0.185)), url("https://img.icons8.com/small/16/000000/long-arrow-right.png");
                background-repeat: no-repeat;
                background-position-x: 95%;
                background-position-y: center;
            }
        </style>
    </head>

    <body>
        <div class="card">
            <div class="row">
                <div class="col-md-8 cart" id="listCartDetail">
                    <div class="title">
                        <div class="row">
                            <div class="col">
                                <h4><b>Shopping Cart</b></h4>
                            </div>
                            <div class="col align-self-center text-right text-muted"></div>
                        </div>
                    </div>

                </div>

                <div class="col-md-4 summary">
                    <div>
                        <h5><b>Summary</b></h5>
                    </div>
                    <hr>
                    <div class="row">
                    </div>
                    <form id="check_out_form" action="CreateSaleOrder" method="get" hidden="">
                    </form>
                    <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                        <div class="col">TOTAL PRICE</div>
                        <div class="col text-right" id="totalPriceCart"> 00.00 VND</div>
                    </div>
                    <button type="button" class="btn" onclick="submitCheckOut()">Mua hàng</button>
                </div>
            </div>

        </div>
        <form action="ViewCart" hidden="" id="formReload"></form>
        <form action="ViewCart" hidden="" id="formReload2">
            <input type="text" name="clear_success" value="1">
        </form>

    </body>
    <script>
        <c:if test="${cartDetailsJson!=null}">
            const cartDetailsJson = JSON.parse('${cartDetailsJson}');
        </c:if>
        var cart = JSON.parse(getCookie("cart"));
        console.log(getCookie('cart'));
         <c:if test="${buy_success!=null}">
            cart.length = 0;
            setCookie('cart', JSON.stringify(cart), 3);
            console.log(getCookie('cart'));
            document.getElementById("formReload2").submit();
        </c:if>
            <c:if test="${clear_success!=null}">
            window.alert("Đã mua thành công");
        </c:if>
        console.log(cartDetailsJson);
        document.getElementById("totalPriceCart").innerHTML = getTotalPrice() + " VND";
        cartDetailsJson.forEach(cartDetail => {
            var totalPrice = 0;
            var newRow = document.createElement('div');
            newRow.class = 'row border-top border-bottom';
            newRow.id = 'product_row_' + cartDetail.product.productId;
            var unit_name = "";
            cartDetail.product.productUnits.forEach(productU =>{
                if(productU.productUnitId==cartDetail.selectedUnit){
                    unit_name = productU.unit.unitName;
                }
            });
            var rowContent = `<div class="row main align-items-center">
                            <div class="col-2"><img class="img-fluid" src="` + cartDetail.product.productImages[0].imageBase64 + `"></div>
                            <div class="col">
                                <div class="row text-muted">`+cartDetail.product.productName+`</div>
                            </div>
                            <div class="col">
                                <div class="row text-muted">Hộp</div>
                            </div>
                            <div class="col">
                                <a href="#" onclick="minus(` + cartDetail.product.productId + `)">-</a><a href="#" class="border" id="quantity_` + cartDetail.product.productId + `">` + cartDetail.quantity + `</a><a onclick="plus(` + cartDetail.product.productId + `)" href="#">+</a>
                            </div>
                            <div class="col"> <div id="price_product_` + cartDetail.product.productId + `">`+getItemPrice(cartDetail.product.productId)+` VND</div> <span class="close" onclick="deleteRow(` + cartDetail.product.productId + `)">&#10005;</span></div>
                        </div>`;
            newRow.innerHTML = rowContent;
            document.getElementById("listCartDetail").appendChild(newRow);
        });
        function submitCheckOut(){
            var check = true;
            if(cart.length==0){
                check = false;
                window.alert("Không có sản phẩm nào trong giỏ hàng");
            }
            if(check){
                document.getElementById("check_out_form").submit();
            }
        }
        function updateCart() {
            setCookie('cart', JSON.stringify(cart), 3);
            console.log(getCookie('cart'));
            document.getElementById("formReload").submit();
        }
        function getItemPrice(product_id) {
            var totalPr = 0;
            cartDetailsJson.forEach(cartDetail => {
                if (cartDetail.product.productId == product_id) {
                    cartDetail.product.productUnits.forEach(product_unit => {
                        if (product_unit.unit.unitId == cartDetail.selectedUnit) {
                            totalPr = totalPr + product_unit.sellPrice * cartDetail.quantity;
                        }
                    });
                }
            });
            return totalPr;
        }
        function getTotalPrice() {
            var totalPr = 0;
            cartDetailsJson.forEach(cartDetail => {
                cartDetail.product.productUnits.forEach(product_unit => {
                    if (product_unit.unit.unitId == cartDetail.selectedUnit) {
                        totalPr = totalPr + product_unit.sellPrice * cartDetail.quantity;
                    }
                });
            });
            return totalPr;
        }
        function minus(product_id) {
            var quanStr = document.getElementById("quantity_" + product_id).innerHTML;
            var quanInt = parseInt(quanStr);
            if (quanInt <= 1) {

            } else {
                quanInt = quanInt - 1;
                document.getElementById("quantity_" + product_id).innerHTML = quanInt + '';
            }
            var cartLe = cart.length;
            for (i = 0; i < cartLe; i++) {
                var cartArr = cart[i].split('_');
                if (cartArr[0] == product_id) {
                    var newRow = cartArr[0] + "_" + cartArr[1] + "_" + quanInt;
                    cart[i] = newRow;
                }
            }
            console.log(cart);
            updateCart()
        }
        function plus(product_id) {
            var quanStr = document.getElementById("quantity_" + product_id).innerHTML;
            var quanInt = parseInt(quanStr);
            quanInt = quanInt + 1;
            document.getElementById("quantity_" + product_id).innerHTML = quanInt + '';
            var cartLe = cart.length;
            for (i = 0; i < cartLe; i++) {
                var cartArr = cart[i].split('_');
                if (cartArr[0] == product_id) {
                    var newRow = cartArr[0] + "_" + cartArr[1] + "_" + quanInt;
                    cart[i] = newRow;
                }
            }
            console.log(cart);
            updateCart()
        }
        function deleteRow(product_id) {
            document.getElementById('product_row_' + product_id).remove();
            var cartLe = cart.length;
            for (i = 0; i < cartLe; i++) {
                var cartArr = cart[i].split('_');
                if (cartArr[0] == product_id) {
                    cart.splice(i, 1);
                    break;
                }
            }
            console.log(cart);
            updateCart()
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</html>
