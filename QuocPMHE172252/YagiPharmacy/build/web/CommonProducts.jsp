<%-- 
    Document   : CommonProducts
    Created on : Nov 3, 2024, 7:58:42 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .card-img {
                height: 190px;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <jsp:include page="./layout/header.jsp"/>
        <div class="container">
            <div class="d-flex flex-row">
                <!-- Sidebar Filter -->
                <div class="card" style="width: 20%">
                    <div class="card-header bg-light">
                        <h6 class="mb-0">Bộ lọc nâng cao</h6>
                    </div>
                    <div class="card-body">
                        <div class="accordion" id="filterAccordion">
                            <form action="CommonProducts" method="get" id="formFilter">
                                <input type="text" name="size_of_page" id="size_of_page" value="${size_of_page}" readonly="" hidden="">
                                <input type="text" name="see_more" value="false" id="see_more" hidden="">
                                <!-- Loại sản phẩm -->
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="headingProductType">
                                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseProductType" aria-expanded="true" aria-controls="collapseProductType">
                                            Loại sản phẩm
                                        </button>
                                    </h2>
                                    <div id="collapseProductType" class="accordion-collapse collapse show" aria-labelledby="headingProductType">
                                        <div class="accordion-body">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="all" id="type_0" name="type" checked>
                                                <label class="form-check-label" for="type_0">Tất cả</label>
                                            </div>
                                            <c:forEach items="${pCates}" var="pCate">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" value="${pCate.productCategoryId}" id="type_${pCate.productCategoryId}" name="type" ${(pCate.productCategoryId.toString())==type_value?"checked":""}>
                                                    <label class="form-check-label" for="type_${pCate.productCategoryId}">${pCate.productCategoryName}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <!-- Đối tượng sử dụng -->
                                <div class="accordion-item mt-3">
                                    <h2 class="accordion-header" id="headingUserTarget">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseUserTarget" aria-expanded="true" aria-controls="collapseUserTarget">
                                            Đối tượng sử dụng
                                        </button>
                                    </h2>
                                    <div id="collapseUserTarget" class="accordion-collapse collapse" aria-labelledby="headingUserTarget">
                                        <div class="accordion-body">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="all" id="target_0" name="target" checked>
                                                <label class="form-check-label" for="target_0">Tất cả</label>
                                            </div>
                                            <%int count=1;%>
                                            <c:forEach items="${targets}" var="target">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" value="${target}" id="target_<%=count%>" name="target" ${target==target_value?"checked":""}>
                                                    <label class="form-check-label" for="target_<%=count%>">${target}</label>
                                                </div>
                                                <%count++;%>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <!-- Giá bán -->
                                <div class="accordion-item mt-3">
                                    <h2 class="accordion-header" id="headingUserTarget">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSalePrice" aria-expanded="true" aria-controls="collapseSalePrice">
                                            Giá bán
                                        </button>
                                    </h2>
                                    <div id="collapseSalePrice" class="accordion-collapse collapse" aria-labelledby="headingSalePrice">
                                        <div class="accordion-body">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="all" id="salePrice_0" name="sale_price" checked>
                                                <label class="form-check-label" for="allSalePice">Tất cả</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="0" id="salePrice_1" name="sale_price" ${sale_price_value=="0"?"checked":""}>
                                                <label class="form-check-label" for="salePrice_1">Dưới 100.000đ</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="1" id="salePrice_2" name="sale_price" ${sale_price_value=="1"?"checked":""}>
                                                <label class="form-check-label" for="salePrice_2">100.000đ đến 300.000đ</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="2" id="salePrice_3" name="sale_price" ${sale_price_value=="2"?"checked":""}>
                                                <label class="form-check-label" for="salePrice_3">300.000đ đến 500.000đ</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="3" id="salePrice_4" name="sale_price" ${sale_price_value=="3"?"checked":""}>
                                                <label class="form-check-label" for="salePrice_4">Trên 500.000đ</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Loại thuốc -->
                                <div class="accordion-item mt-3">
                                    <h2 class="accordion-header" id="headingDrugKind">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseDrugKind" aria-expanded="true" aria-controls="collapseDrugKind">
                                            Loại thuốc
                                        </button>
                                    </h2>
                                    <div id="collapseDrugKind" class="accordion-collapse collapse" aria-labelledby="headingDrugKind">
                                        <div class="accordion-body">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="all" id="drugKind_0" name="drug_kind" checked>
                                                <label class="form-check-label" for="allSalePice">Tất cả</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="true" id="drugKind_1" name="drug_kind" ${drug_kind_value=="true"?"checked":""}>
                                                <label class="form-check-label" for="drugKind_1">Thuốc kê đơn</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="false" id="drugKind_2" name="drug_kind" ${drug_kind_value=="false"?"checked":""}>
                                                <label class="form-check-label" for="drugKind_2">Thuốc không kê đơn</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Nước sản xuất -->
                                <div class="accordion-item mt-3">
                                    <h2 class="accordion-header" id="headingCountry">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCountry" aria-expanded="true" aria-controls="collapseCountry">
                                            Nước sản xuất
                                        </button>
                                    </h2>
                                    <div id="collapseCountry" class="accordion-collapse collapse" aria-labelledby="headingCountry">
                                        <div class="accordion-body">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="all" id="country_0" name="country" checked>
                                                <label class="form-check-label" for="allCountry">Tất cả</label>
                                            </div>
                                            <%int count2=1;%>
                                            <c:forEach items="${countries}" var="country">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" value="${country}" id="country_<%=count2%>" name="country" ${country_value==country?"checked":""}>
                                                    <label class="form-check-label" for="country_<%=count2%>">${country}</label>
                                                </div>
                                                <%count2++;%>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>

                                <!-- Thương hiệu -->
                                <div class="accordion-item mt-3">
                                    <h2 class="accordion-header" id="headingBrand">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseBrand" aria-expanded="true" aria-controls="collapseBrand">
                                            Thương hiệu
                                        </button>
                                    </h2>
                                    <div id="collapseBrand" class="accordion-collapse collapse" aria-labelledby="headingBrand">
                                        <div class="accordion-body">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" value="all" id="brand_0" name="brand" checked>
                                                <label class="form-check-label" for="allBrand">Tất cả</label>
                                            </div>
                                            <%int count3=1;%>
                                            <c:forEach items="${brands}" var="brand">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" value="${brand}" id="brand_<%=count3%>" name="brand" ${brand_value==brand?"checked":""}>
                                                    <label class="form-check-label" for="brand_<%=count3%>">${brand}</label>
                                                </div>
                                                <%count3++;%>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!-- End Sidebar Filter -->
                <!-- Product List -->
                <div id="product_list_area" class="card d-flex flex-row flex-wrap" style="width: 100%; height: fit-content;border: none">
                    <!--                    <div class="card" style="width: calc(25% - 30px);height: 410px;margin-top: 20px;margin-left: 10px;margin-right: 10px">
                                            <img src=""
                                                 class="card-img-top card-img" alt="...">
                                            <div class="card-body card-body-permanent">
                                                <h5 class="card-title"></h5>
                                                <div class="card-text" style="overflow: hidden; height: 72px;width: 180px"></div>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"></li>
                                                <li class="list-group-item"></li>
                                            </ul>
                                            <div class="card-body" style="display: flex;flex-direction: column;justify-content: center">
                                                <a href="#" class="btn" style="width: 190px;height: 38px;border-radius: 20px;background-color: #306de4;color: #FFF">Chọn mua</a>
                                            </div>
                                        </div>
                                        <div class="card" style="width: calc(25% - 30px);height: 410px;margin-top: 20px;margin-left: 10px;margin-right: 10px">
                                            <img src=""
                                                 class="card-img-top card-img" alt="...">
                                            <div class="card-body card-body-permanent">
                                                <h5 class="card-title"></h5>
                                                <div class="card-text" style="overflow: hidden; height: 72px;width: 180px"></div>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"></li>
                                                <li class="list-group-item"></li>
                                            </ul>
                                            <div class="card-body" style="display: flex;flex-direction: column;justify-content: center">
                                                <a href="#" class="btn" style="width: 190px;height: 38px;border-radius: 20px;background-color: #306de4;color: #FFF">Chọn mua</a>
                                            </div>
                                        </div>
                    
                                        <div class="card" style="width: calc(25% - 30px);height: 410px;margin-top: 20px;margin-left: 10px;margin-right: 10px">
                                            <img src=""
                                                 class="card-img-top card-img" alt="...">
                                            <div class="card-body card-body-permanent">
                                                <h5 class="card-title"></h5>
                                                <div class="card-text" style="overflow: hidden; height: 72px;width: 180px"></div>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"></li>
                                                <li class="list-group-item"></li>
                                            </ul>
                                            <div class="card-body" style="display: flex;flex-direction: column;justify-content: center">
                                                <a href="#" class="btn" style="width: 190px;height: 38px;border-radius: 20px;background-color: #306de4;color: #FFF">Chọn mua</a>
                                            </div>
                                        </div>
                                        <div class="card" style="width: calc(25% - 30px);height: 410px;margin-top: 20px;margin-left: 10px;margin-right: 10px">
                                            <img src=""
                                                 class="card-img-top card-img" alt="...">
                                            <div class="card-body card-body-permanent">
                                                <h5 class="card-title"></h5>
                                                <div class="card-text" style="overflow: hidden; height: 72px;width: 180px"></div>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"></li>
                                                <li class="list-group-item"></li>
                                            </ul>
                                            <div class="card-body" style="display: flex;flex-direction: column;justify-content: center">
                                                <a href="#" class="btn" style="width: 190px;height: 38px;border-radius: 20px;background-color: #306de4;color: #FFF">Chọn mua</a>
                                            </div>
                                        </div>
                    
                                        <div class="card" style="width: calc(25% - 30px);height: 410px;margin-top: 20px;margin-left: 10px;margin-right: 10px">
                                            <img src=""
                                                 class="card-img-top card-img" alt="...">
                                            <div class="card-body card-body-permanent">
                                                <h5 class="card-title"></h5>
                                                <div class="card-text" style="overflow: hidden; height: 72px;width: 180px"></div>
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item"></li>
                                                <li class="list-group-item"></li>
                                            </ul>
                                            <div class="card-body" style="display: flex;flex-direction: column;justify-content: center">
                                                <a href="#" class="btn" style="width: 190px;height: 38px;border-radius: 20px;background-color: #306de4;color: #FFF">Chọn mua</a>
                                            </div>
                                        </div>-->
                    
                </div>



            </div>
        </div>
        <!-- footer -->
        <jsp:include page="./layout/footer.jsp"/>
        <!-- end footer -->
    </body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
                        const products = JSON.parse('${jsonProducts}');
                        console.log(products);
                        products.forEach(product => {
                            document.getElementById("product_list_area").innerHTML += createProductCard(product);
                        });
                        document.getElementById("product_list_area").innerHTML += `<div style="width:100%;display:flex;flex-direction:column;justify-content:center!important;"><a style="margin-left:45%;margin-right:45%;" href="#" onclick="seeMore()">Xem thêm</a></div>`;
                        function createProductCard(product) {
                            var le = product.productUnits.length;
                            var lastPrice = product.productUnits[le - 1];
                            var stringPrice = formatNumber(lastPrice.sellPrice) + 'đ ' + '/ ' + lastPrice.unit.unitName;
                            var newCart = `<div class="card" style="width: calc(25% - 30px);height: 410px;margin-top: 10px;margin-left: 10px;margin-right: 10px">
                        <img src="` + product.productImages[0].imageBase64 + `"
                             class="card-img-top card-img" alt="...">
                        <div class="card-body card-body-permanent">
                            <div class="card-text" style="overflow: hidden; height: 40px;width: 180px">` + product.productName + `</div>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">` + stringPrice + `</li>
                            <li class="list-group-item">` + product.productSpecification + `</li>
                        </ul>
                        <div class="card-body" style="display: flex;flex-direction: column;justify-content: center">
                            <a href="#" class="btn" style="width: 190px;height: 38px;border-radius: 20px;background-color: #306de4;color: #FFF">Chọn mua</a>
                        </div>
                    </div>`;
                            return newCart;
                        }
                        function formatNumber(number) {
                            return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        }
                        function seeMore(){
                            document.getElementById('see_more').value = 'true';
                            document.getElementById('formFilter').submit();
                        }
    </script>
</html>
