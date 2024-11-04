<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Product List</title>
        <link rel="shortcut icon" type="image/x-icon" href="https://freevector-images.s3.amazonaws.com/uploads/vector/preview/36682/36682.png" />
        <link rel="stylesheet" href="./css/main.css">
        <link rel="stylesheet" href="./css/font-awesome.min.css">
        <link rel="stylesheet" href="./css/bootstrap.css">

    </head>

    <body class="biolife-body">
        <c:if test="${param['index']==null }">   
            <c:set var = "index" scope = "page" value = "1"/>
        </c:if>
        <c:if test="${param['index']!=null}">
            <c:set var = "index" scope = "page" value = "${param['index']}"/>
        </c:if>
        <jsp:include page="./layout/header.jsp"/>
        <!--Navigation  section-->
        <section class="section-product-list">
            <div class="container">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="./HomePage">Home </a></li>
                        <li class="breadcrumb-item"><a href="./NewList?index=1">News List</a></li>
                    </ol>
                </nav>
                <div class="main-section-content">
                    <div class="sider">
                        <div class="search">
                            <form action="NewList?index=1" class="form-search" method="get">
                                <input type="text" name="search" class="input-text" value="${param['search']}" placeholder="Search news here...">
                                <button type="submit" class="btn-submit"><i class="fa fa-search"></i></button>
                            </form>

                            <div class="latest-product">
                                <h5>Latest  News</h5>
                                <c:forEach var="pro" items="${bloList}">
                                    <div style="border: 1px solid lightgray;padding: 5px;margin-bottom: 10px">
                                        <h5 class="latest-product-title"  ><a href="BlogDetail?id=${pro.newsId}" class="product-name" style="font-weight: bold;text-decoration: none; color: black">${pro.newsTitle}</a></h5>
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
                        
                        <div class="product-list">
                            <c:forEach var="p" items="${list}">
                                <div class="product-item" style="padding: 15px;">
                                    <a href="BlogDetail?id=${p.newsId}" title="product thumbnail" ><img src="${p.newsImage}" alt="" width="100%"></a>
                                    <div class="product-info">
                                        <h4 class="product-title"><a href="BlogDetail?id=${p.newsId}" class="product-name">${p.newsTitle}</a></h4>
                                        <div class="product-description">
                                            <p>${p.newsHashtag}</p>
                                        </div>

                                    </div>
                                    <div class="btn-list" style="margin-top: 15px;">
                                        <a href="BlogDetail?id=${p.newsId}" class="product-btn" style="color: white; background-color: #1da1f2;border-radius: 10px;padding: 5px; text-align: center;"> Show</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="pagination-arena">
                            <ul class="pagination">
                                <li class="page-item"><a href="./NewList?categoryId=${param['categoryId']}&index=1&search=${param['search']}" class="page-link"><i class="fa fa-angle-left" aria-hidden="true"></i></a></li>
                                <li class="page-item">
                                    <a href="./NewList?categoryId=${param['categoryId']}&index=${index-2}&search=${param['search']}" class="page-link ${index-2<1?"d-none":""}">${index-2}</a></li>
                                <li class="page-item">
                                    <a href="./NewList?categoryId=${param['categoryId']}&index=${index-1}&search=${param['search']}" class="page-link ${index-1<1?"d-none":""}">${index-1}</a></li>
                                <li class="page-item active">
                                    <a href="./NewList?categoryId=${param['categoryId']}&index=${index}&search=${param['search']}" class="page-link">${index}</a></li>
                                <li class="page-item">
                                    <a href="./NewList?categoryId=${param['categoryId']}&index=${index+1}&search=${param['search']}" class="page-link ${index+1>numberPage?"d-none":""}" >${index+1}</a></li>
                                <li class="page-item">
                                    <a href="./NewList?categoryId=${param['categoryId']}&index=${index+2}&search=${param['search']}" class="page-link ${index+2>numberPage?"d-none":""}">${index+2}</a></li>
                                <li><a href="./NewList?categoryId=${param['categoryId']}&index=${numberPage}&search=${param['search']}" class="page-link"><i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                            </ul>
                        </div>          
                    </div>
                </div>
            </div>

        </section>

        <jsp:include page="./layout/footer.jsp"/>
        <!-- Scroll Top Button -->
        <a class="btn-scroll-top"><i class="biolife-icon icon-left-arrow"></i></a>

        <script src="./js/bootstrap.js"></script>
        <script src="./js/main.js"></script>

    </body>

</html>