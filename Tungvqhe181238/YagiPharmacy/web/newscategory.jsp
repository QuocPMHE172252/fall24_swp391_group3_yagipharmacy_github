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
            <div class="container-fluid">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb" style="margin: 00px">
                        <li class="breadcrumb-item"><a href="./HomePage" style="text-decoration: none; color: #007bff">Home </a></li>
                        <li class="breadcrumb-item"><a href="./NewCategoryList" style="text-decoration: none; color: black">News Categories List</a></li>
                    </ol>
                </nav>
                <img src="https://res.cloudinary.com/dplelrpij/image/upload/v1730539193/lixlamuh20owohthxacs.png" width="100%"/>
                <div class="container">
                    <c:forEach var="cat" items="${listParent}">
                        <div>
                            <i class="fa fa-plus-circle" aria-hidden="true" style="color: #3b62dc"></i> ${cat.newsCategoryName}<span style="margin: 0px 10px; color: lightgray">|</span><a href="./NewList?categoryId=${cat.newsCategoryId}&index=1&search=" style="text-decoration: none; color: #007bff">View News List ></a>
                        </div>
                        <div>
                            <div class="row">
                                <c:forEach var="con" items="${cat.categories}">

                                    <div class="col-md-3">
                                        <div style="margin: 10px; background-color: #f4f6fb; display: flex; justify-content: center; align-items: center; flex-direction: column; height: 170px;">
                                            <img src="${con.newsImg}" width="90px" height="90px" style="border-radius: 50%" alt="alt"/>
                                            <b><a  href="./NewList?categoryId=${con.newsCategoryId}&index=1&search=">${con.newsCategoryName}</a></b>
                                            <P> ${con.numberNews} news</p>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </c:forEach>

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