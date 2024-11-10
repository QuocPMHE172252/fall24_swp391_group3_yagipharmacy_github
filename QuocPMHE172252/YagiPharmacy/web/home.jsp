<%-- 
    Document   : home
    Created on : May 22, 2022, 2:59:33 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
        <link rel="shortcut icon" type="image/x-icon" href="https://freevector-images.s3.amazonaws.com/uploads/vector/preview/36682/36682.png" />
        <link rel="stylesheet" href="./css/main.css">
        <link rel="stylesheet" href="./css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/bootstrap.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>

    <body class="biolife-body">
        <jsp:include page="./layout/header.jsp"/>
        
        <div class="container">
            <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item  active">
                        <a href="#"><img src="https://cdn.nhathuoclongchau.com.vn/unsafe/828x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/banner_web_pc_1610x492_c9e94cade9.jpg" class="d-block w-100" alt="value"></a>
                        <div class="carousel-caption d-none d-md-block">
                        </div>
                    </div>


                    <div class="carousel-item ">
                        <a href="#"><img src="https://cdn.nhathuoclongchau.com.vn/unsafe/828x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/1610x492_banner_web_100_9d405f1df2.jpg" class="d-block w-100" alt="value"></a>
                        <div class="carousel-caption d-none d-md-block">
                        </div>
                    </div>
                    <div class="carousel-item ">
                        <a href="#"><img src="https://cdn.nhathuoclongchau.com.vn/unsafe/828x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/Banner_Web_PC_1610x492_8459705aa6.png" class="d-block w-100" alt="value"></a>
                        <div class="carousel-caption d-none d-md-block">
                        </div>
                    </div>
                    <div class="carousel-item ">
                        <a href="#"><img src="https://cdn.nhathuoclongchau.com.vn/unsafe/828x0/filters:quality(90)/https://cms-prod.s3-sgn09.fptcloud.com/1610x492_Banner_WEB_316e6dc5c1.png" class="d-block w-100" alt="value"></a>
                        <div class="carousel-caption d-none d-md-block">
                        </div>
                    </div>

                </div>

                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        <section class="section-product-list">
            <div class="container">
                <div class="main-section-content">
                    <div class="sider">
                        <div class="search">

                            <div class="latest-product">
                                <h5>Latest  Post</h5>
                                <c:forEach var="pro" items="${bloList}">
                                    <div style="border: 1px solid lightgray;padding: 5px;margin-bottom: 10px">
                                        <h5 class="latest-product-title"  ><a href="NewsDetail?nid=${pro.newsId}" class="product-name" style="font-weight: bold;text-decoration: none; color: black">${pro.newsTitle}</a></h5>
                                        <div class="latest-product-item">
                                            <a href="BlogDetail?id=${pro.newsId}" title="product thumbnail" style="width: 45%;"><img src="${pro.newsImage}" alt="" width="100%"></a>
                                            <div class="latest-product-info">
                                                <p class="latest-product-title product-description" style="padding-left: 8px;" ><a href="BlogDetail?id=${pro.newsId}" class="product-name" style="font-size: 12px;">${pro.newsHashtag}</a></p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="display-list">
                        <div>
                            <h1 style="text-align: center" >Featured Product</h1>
                        </div>
                        <div class="product-list">
                            <c:forEach var="pro" items="${prolist}">
                                <div class="product-item">
                                    <a href="ProductDetail?pid=${pro.productId}" title="product thumbnail" ><img src="${pro.productImages.get(0).imageBase64}" alt="" width="100%"></a>
                                    <div class="product-info">
                                        <h4 class="product-title"><a href="ProductDetail?pid=${pro.productId}" class="product-name">${pro.productName}</a></h4>
                                        <div class="price">
                                            <p><span class="price-amount" id="price_sale">${pro.getLastUnit().formatPrice()}<span>đ</span>/${pro.getLastUnit().unit.unitName}</span></p>
                                        </div>
                                        <div class="product-description">
                                            <p>${pro.brand}</p>
                                        </div>
                                        <div class="btn-list" style="display: flex;flex-direction: row;justify-content: center">
                                            <a href="ProductDetail?pid=${pro.productId}" class="product-btn" onclick=""><i class="fa fa-eye"></i>Chọn mua</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>

                </div>
            </div>

        </section>
        <section class="section-product-list">
            <div class="container">
                <div class="main-section-content" style="justify-content: center;">
                    <div class="display-list">
                        <div>
                            <h1 class="text-center" >Hot Post</h1>
                        </div>
                        <div class="product-list">
                            <c:forEach var="p" items="${hotList}">
                                <div class="product-item" style="padding: 15px;">
                                    <a href="BlogDetail?id=${p.newsId}" title="product thumbnail" ><img src="${p.newsImage}" alt="" width="100%"></a>
                                    <div class="product-info">
                                        <h4 class="product-title"><a href="BlogDetail?id=${p.newsId}" class="product-name">${p.newsTitle}</a></h4>
                                        <div class="product-description">
                                            <p>${p.newsHashtag}</p>
                                        </div>

                                    </div>
                                    <div class="btn-list" style="margin-top: 15px;">
                                        <a href="NewsDetail?nid=${p.newsId}" class="product-btn" style="color: white; background-color: #1da1f2;border-radius: 10px;padding: 5px; text-align: center;"> Show</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>

                </div>
            </div>

        </section>
        <jsp:include page="./layout/footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    </body>

</html>
