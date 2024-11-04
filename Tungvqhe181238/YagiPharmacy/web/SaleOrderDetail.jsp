<%-- 
    Document   : SaleOrderDetail
    Created on : Nov 4, 2024, 1:54:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="shortcut icon" type="image/x-icon" href="https://freevector-images.s3.amazonaws.com/uploads/vector/preview/36682/36682.png" />
        <link rel="stylesheet" href="./css/main.css">
        <link rel="stylesheet" href="./css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/bootstrap.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <style>
            .step-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 30px;
            }
            .step {
                display: flex;
                flex-direction: column;
                align-items: center;
                width: 100px;
            }
            .step-circle {
                width: 50px;
                height: 50px;
                border: 2px solid #000;
                border-radius: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 24px;
            }
            .step-circle.completed {
                background-color: #e9ecef;
                color: black;
            }
            .step-line {
                flex-grow: 1;
                height: 2px;
                background-color: #000;
                margin-top: 25px;
            }
            .product-img {
                width: 70px;
                height: 50px;
                background-color: #5a8df4;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .info-section {
                padding: 15px;
                border: 1px solid #e9ecef;
                background-color: #fff;
                margin-bottom: 15px;
            }
            body {
                background-color: #f8f5f2;
            }
        </style>
    </head>
    <body>
        <!-- header -->
        <jsp:include page="./layout/header.jsp"/>
        <!-- end header -->


        <div class="container mt-5">
            <!-- Back Button -->
            <a href="CustomerOrders" class="btn btn-light mb-4">Back</a>

            <!-- Steps Section -->
            <div class="step-container">
                <div class="step">
                    <div class="step-circle" id="process_1"></div>
                    <span>Có hàng</span>
                </div>
                <div class="step-line"></div>
                <div class="step">
                    <div class="step-circle" id="process_2"></div>
                    <span>Đang giao</span>
                </div>
                <div class="step-line" id="process_3"></div>
                <div class="step">
                    <div class="step-circle"></div>
                    <span>Đã nhận</span>
                </div>
            </div>

            <!-- Customer Information Section -->
            <div class="info-section">
                <p><strong id="receiver_name">Người nhận:</strong> </p>
                <p><strong id="receiver_address">Địa chỉ:</strong></p>
                <p><strong id="receiver_phone">Số điện thoại:</strong></p>
                <p><strong id="total_price">Tổng giá đơn:</strong></p>
                <button class="btn btn-outline-danger">Đã nhận hàng</button>
            </div>

            <!-- Product Information Section -->
            <div class="info-section" id="product_area">
                <p><strong>Product information:</strong></p>
            </div>
        </div>

        <!-- footer -->
        <jsp:include page="./layout/footer.jsp"/>
        <!-- end footer -->
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const  saleOrder = JSON.parse('${saleOrderJson}');
        console.log(saleOrder);
        document.getElementById('receiver_name').innerHTML +=' '+saleOrder.receiverName;
        document.getElementById('receiver_address').innerHTML += ' '+saleOrder.specificAddress;
        document.getElementById('receiver_phone').innerHTML += ' '+saleOrder.receiverPhone;
        document.getElementById('total_price').innerHTML += ' '+saleOrder.totalPrice.toLocaleString('en-US')+'VND';
        
        saleOrder.saleOrderDetails.forEach(saleOrderDetail => {
            document.getElementById('product_area').innerHTML += createRow(saleOrderDetail);
        });
        if(saleOrder.processing==0){
            
        }
        if(saleOrder.processing>=1){
            document.getElementById('process_1').innerHTML = '✓';
            document.getElementById('process_1').classList.add('completed');
        }
        if(saleOrder.processing==2){
            document.getElementById('process_2').innerHTML = '✓';
            document.getElementById('process_2').classList.add('completed');
            
        }
        if(saleOrder.processing==3){
            document.getElementById('process_3').innerHTML = '✓';
            document.getElementById('process_3').classList.add('completed');
            
        }
        
        function createRow(saleOrderDetail){
            var newRow = `<div class="d-flex align-items-center">
                    <div class="product-img"><img style="height:50px;width:70px" src="`+saleOrderDetail.product.productImages[0].imageBase64+`" alt="alt"/></div>
                        <div class="ms-3">
                            <p><strong>`+saleOrderDetail.product.productName+`</strong></p>
                            <p>`+saleOrderDetail.product.productDescription+`</p>
                        </div>
                    </div>`;
            return newRow;
        }
    </script>
</html>
