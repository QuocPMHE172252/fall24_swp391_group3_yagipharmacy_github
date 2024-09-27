<%-- 
    Document   : ListNews
    Created on : Sep 22, 2024, 11:37:00 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap demo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary container">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Link</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Dropdown
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="container" style="margin-top: 100px">
            <form class="d-flex form-group" role="search" action="ListNews" method="get">
                <table border="0">
                    <thead>
                        <tr>
                            <th>Danh mục:</th>
                            <th>Danh mục con:</th>
                            <th>Từ ngày:</th>
                            <th>Đến ngày:</th>
                            <th>Tiêu đề:</th>
                            <th>Thẻ gắn:</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><select class="form-control" id="parent_cate" name="parent_cate" onchange="changeListChild()">
                                    <option value="all">All</option>
                                    <c:forEach items="${listParentCates}" var="parent">
                                        <option value="${parent.newsCategoryId}" ${parent_cate == parent.newsCategoryId.toString()?"selected":""}>${parent.newsCategoryName}</option>
                                    </c:forEach>
                                </select></td>
                            <td><select class="form-control" id="cateId" name="cateId">
                                    <option value="all">All</option>
                                </select></td>
                            <td><input class="form-control me-2" type="date" name="from" value="${from}"></td>
                            <td><input class="form-control me-2" type="date" name="to" value="${to}"></td>
                            <td><input class="form-control me-2" type="search" placeholder="Tiêu đề" name="title" value="${title}"></td>
                            <td><input class="form-control me-2" type="search" placeholder="Thẻ được gắn" name="hashtag" value="${hashtag}"></td>
                            <td><button class="btn btn-outline-success" type="submit">Search</button></td>
                        </tr>
                    </tbody>
                </table>                
            </form><br>
            <div class="card-header">
                <a href="./AddNews" class="btn btn-primary">Add News</a>
            </div>
        </div>


        <table class="table table-striped container" style="margin-top: 100px">

            <thead>
                <tr>
                    <th scope="col">News image</th>
                    <th scope="col">News title</th>
                    <th scope="col">Change status</th>
                    <th scope="col">Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="e">
                    <tr>
                        <th><img src="${e.newsImage}" alt="alt" height="100px" width="100px"></th>
                        <td><a target="_blank" href="../NewsDetail?nid=${e.newsId}">${e.newsTitle}</a></td>
                        <td><button type="button" class='btn ${e.isDeleted()?"btn-success":"btn-danger"}'>${e.isDeleted()?"Active":"Delete"}</button></td>
                        <td><a class="btn btn-success" href="UpdateNews?newsId=${e.newsId}" target="_blank">Update</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
                                var childListJson = '${listChildJson}';
                                var listChild = JSON.parse(childListJson);
                                changeListChild();
                                function changeListChild() {
                                    var parentId = document.getElementById("parent_cate").value;
                                    document.getElementById("cateId").innerHTML = '<option value="all">All</option>';
                                    listChild.forEach((child) => {
                                        if (child.newsCategoryParentId == parentId) {
                                            let selectOption = '<option value="' + child.newsCategoryId + '">' + child.newsCategoryName + '</option>';
                                            if ("${cateId}" == child.newsCategoryId) {
                                                selectOption = '<option selected value="' + child.newsCategoryId + '">' + child.newsCategoryName + '</option>';
                                            }
                                            document.getElementById("cateId").innerHTML += selectOption;
                                        }
                                    });
                                }
    </script>
</html>
