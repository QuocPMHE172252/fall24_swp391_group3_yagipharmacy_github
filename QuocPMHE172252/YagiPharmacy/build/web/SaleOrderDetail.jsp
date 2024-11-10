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
                    <div class="step-circle" id="process_0"></div>
                    <span>Chờ xác nhận</span>
                </div>
                <div class="step-line"></div>
                <div class="step">
                    <div class="step-circle" id="process_2"></div>
                    <span>Chuẩn bị</span>
                </div>
                <div class="step-line"></div>
                <div class="step">
                    <div class="step-circle" id="process_3"></div>
                    <span>Đang giao</span>
                </div>
                <div class="step-line"></div>
                <div class="step">
                    <div class="step-circle" id="process_4"></div>
                    <span>Đã nhận</span>
                </div>
            </div>

            <!-- Customer Information Section -->
            <div class="info-section">
                <p><strong id="receiver_name">Người nhận:</strong> </p>
                <p><strong id="receiver_address">Địa chỉ:</strong></p>
                <p><strong id="receiver_phone">Số điện thoại:</strong></p>
                <p><strong id="created_date">Thời gian đặt hàng:</strong></p>
                <p><strong id="total_price">Tổng giá đơn:</strong></p>

            </div>
            <div id="subArea"></div>
            <!-- Product Information Section -->
            <div class="info-section" >
                <p><strong>Product information:</strong></p>
                <table class="table table-info tab-pane">

                    <thead>
                        <tr>
                            <th style="">Ảnh</th>
                            <th style="">Thông tin ngắn</th>
                            <th style="">Số lượng</th>
                            <th style="">&nbsp;Giá</th>
                        </tr>
                    </thead>
                    <tbody id="product_area">                        
                    </tbody>
                </table>
            </div>
        </div>

        <!-- footer -->
        <jsp:include page="./layout/footer.jsp"/>
        <!-- end footer -->
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const  saleOrder = JSON.parse(`${saleOrderJson}`);
        console.log(saleOrder);
        document.getElementById('receiver_name').innerHTML += ' ' + saleOrder.receiverName;
        document.getElementById('receiver_address').innerHTML += ' ' + saleOrder.specificAddress;
        document.getElementById('receiver_phone').innerHTML += ' ' + saleOrder.receiverPhone;
        document.getElementById('created_date').innerHTML += ' ' + formatDateTimeVN(saleOrder.createdDate);
        document.getElementById('total_price').innerHTML += ' ' + saleOrder.totalPrice.toLocaleString('en-US') + 'VND';
        function createRow(saleOrderDetail) {
            var newRow = `<tr>
                        <td ><div class="product-img"><img style="height:50px;width:70px" src="` + saleOrderDetail.product.productImages[0].imageBase64 + `" alt="alt"/></div></td>
                        <td >
                            <strong>` + saleOrderDetail.product.productName + `: </strong>` + truncateString(saleOrderDetail.product.productDescription) + `</td>
                        <td >&nbsp;&nbsp;&nbsp;` + saleOrderDetail.quantity + `</td>
                        <td >` + (saleOrderDetail.quantity * saleOrderDetail.productUnit.sellPrice).toLocaleString('en-US') + `</td>
                    </tr>`;
            return newRow;
        }
        saleOrder.saleOrderDetails.forEach(saleOrderDetail => {
            document.getElementById('product_area').innerHTML += createRow(saleOrderDetail);
        });
        if (saleOrder.processing >= 0 && saleOrder.processing != 1) {
            document.getElementById('process_0').innerHTML = '✓';
            document.getElementById('process_0').classList.add('completed');
        }
        if (saleOrder.processing > 1) {
            document.getElementById('process_2').innerHTML = '✓';
            document.getElementById('process_2').classList.add('completed');
        }
        if (saleOrder.processing >= 3) {

            document.getElementById('process_3').innerHTML = '✓';
            document.getElementById('process_3').classList.add('completed');

        }
        if (saleOrder.processing >= 4) {
            document.getElementById('process_4').innerHTML = '✓';
            document.getElementById('process_4').classList.add('completed');

        }
        if (saleOrder.processing == 3) {
            document.getElementById('subArea').innerHTML = `<a class="btn btn-outline-danger" href="CheckRecieved?order_id=` + saleOrder.saleOrderId + `" id="checkNhanHang">Đã nhận hàng</a>`;
        }

        function truncateString(str) {
            // Kiểm tra độ dài của chuỗi
            if (str.length <= 130) {
                return str; // Trả về chuỗi nếu ngắn hơn hoặc bằng 25 kí tự
            } else {
                // Cắt chuỗi và thêm dấu ba chấm
                return str.slice(0, 127) + '...';
            }
        }

        function formatDateTimeVN(dateString) {
            const date = new Date(dateString);

            const day = date.getDate();
            const month = date.getMonth() + 1; // Tháng trong JavaScript bắt đầu từ 0
            const year = date.getFullYear();

            const hours = date.getHours();
            const minutes = date.getMinutes();
            const seconds = date.getSeconds();

            const ampm = hours >= 12 ? 'PM' : 'AM';
            const formattedHours = hours % 12 || 12; // Chuyển đổi sang định dạng 12 giờ

            // Tạo chuỗi ngày tháng theo định dạng tiếng Việt
            const dateStr = `Ngày `+day+` tháng `+month+` năm `+year;
            const timeStr = `lúc `+formattedHours+`:`+minutes+`:`+seconds+``+ampm;

            return dateStr+" "+timeStr;
        }
        
        const inputDate = "Nov 9, 2024, 3:18:15 AM"; 
        const formattedDateda = formatDateTimeVN(inputDate); 
        console.log(formattedDateda);


    </script>
</html>
