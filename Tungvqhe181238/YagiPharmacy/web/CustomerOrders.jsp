<%-- 
    Document   : CustomerOrders
    Created on : Nov 4, 2024, 12:05:49 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </head>
    <body>
        <!-- header -->
        <jsp:include page="./layout/header.jsp"/>
        <!-- end header -->

        <div class="container container-fluid" style="min-height: 540px">
            <table class="table" id="orderListTbl">
                <thead>
                    <tr>
                        <th scope="col">Ngày đặt</th>
                        <th scope="col">Người nhận</th>
                        <th scope="col">SĐT</th>
                        <th scope="col">Email</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Tổng giá trị đơn</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Xem chi tiết</th>
                    </tr>
                </thead>
                <tbody id="data_area">
                </tbody>
            </table>
        </div>

        <!-- footer -->
        <jsp:include page="./layout/footer.jsp"/>
        <!-- end footer -->
    </body>

    <script>
        const saleOrders = JSON.parse('${jsonSaleOrders}');
        console.log(saleOrders);
        saleOrders.forEach(saleOrder =>{
            var newRow = createRow(saleOrder);
            document.getElementById("data_area").innerHTML += newRow;
        });
        function createRow(saleOrder){
            var newRow = `<tr>
                                <td>`+saleOrder.createdDate+`</td>
                                <td>`+saleOrder.receiverName+`</td>
                                <td>`+saleOrder.receiverPhone+`</td>
                                <td>`+saleOrder.receiverEmail+`</td>
                                <td>`+saleOrder.specificAddress+`</td>
                                <td>`+saleOrder.totalPrice.toLocaleString('en-US')+`</td>
                                <td>`+processDefine(saleOrder.processing)+`</td>
                                <td><a href="SaleOrderDetail?order_id=`+saleOrder.saleOrderId+`" class="btn btn-info" style="color:white;">Chi tiết đơn hàng</a></td>
                        </tr>`;
            return newRow;
        }
        function processDefine(processing){
            if(processing==0){
                return 'Chuẩn bị hàng';
            }
            if(processing==1){
                return 'Có hàng';
            }
            if(processing==2){
                return 'Đang giao';
            }
            if(processing==3){
                return 'Đã nhận';
            }
        }
    </script>
</html>
