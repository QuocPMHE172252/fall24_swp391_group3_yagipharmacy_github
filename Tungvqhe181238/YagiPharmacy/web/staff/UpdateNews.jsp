<%-- 
    Document   : AddNews
    Created on : Sep 23, 2024, 2:43:07 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style type="text/css">
            .inf-content {
                border: 1px solid #DDDDDD;
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                box-shadow: 7px 7px 7px rgba(0, 0, 0, 0.3);
            }
        </style>
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
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="container">
            <div class="row flex-lg-nowrap">
                <form action="UpdateNews" method="post">
                    <div class="col">
                        <div class="row">
                            <div class="col mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="e-profile">
                                            <div class="row">
                                                <div class="col-12 col-sm-auto mb-3">
                                                    <img src="${findingNews.newsImage}" alt="" height="450px" width="600px" id="news_image">
                                                </div>

                                            </div>
                                            <div
                                                class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                                <div class="text-center text-sm-left mb-2 mb-sm-0">
                                                    <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap"></h4>
                                                    <p class="mb-0"></p>
                                                    <div class="mt-2">
                                                        <input type="file" accept="image/*" onchange="convertImageToBase64(this)">
                                                        <input type="hidden" name="base64_img" id="base64_img" value="${findingNews.newsImage}">
                                                    </div>
                                                </div>
                                            </div>
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item"><a href class="active nav-link">Update</a></li>
                                            </ul>
                                            <div class="tab-content pt-3">
                                                <div class="tab-pane active">
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Bài viết</label>
                                                                        <input type="hidden" name="newsId" value="${findingNews.newsId}">
                                                                        <textarea class="" name="news_content" id="news_content" value="" rows="30">${findingNews.newsContent}</textarea>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col" style="max-width: 120px">
                                                                    <div class="form-group">
                                                                        <label>Status</label>
                                                                        <select class="form-control small" name="is_deleted" id="is_deleted">
                                                                            <option value="false" ${!findingNews.isDeleted()?"selected":""}>Active</option>
                                                                            <option value="true" ${findingNews.isDeleted()?"selected":""}>Inactive</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <br>
                                                <div class="row">
                                                    <div class="mt-5">
                                                        <a href="ListNews" class="btn btn-primary">Back to News List</a>
                                                    </div>
                                                    <div class="col d-flex justify-content-end">
                                                        <button class="btn btn-primary" type="submit">Save
                                                            Changes</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.tiny.cloud/1/bxb68lg12i11zhb0ei9ubbfw1t388posa12jg0hpmq8570dg/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

    </body>
    <script>
                                                            tinymce.init({
                                                                selector: '#news_content'
                                                            });
        <c:if test="${change!=null&&change==false}">
                                                            window.alert("Cập nhật thất bại");
        </c:if>
        <c:if test="${change!=null&&change==true}">
                                                            window.alert("Cập nhật thành công")
        </c:if>
                                                            function convertImageToBase64(input) {
                                                                const file = input.files[0];
                                                                const reader = new FileReader();
                                                                reader.onloadend = () => {
                                                                    const base64String = reader.result;
                                                                    document.getElementById("news_image").src = base64String;
                                                                    document.getElementById("base64_img").value = base64String;
                                                                    console.log(base64String);
                                                                };
                                                                reader.readAsDataURL(file);
                                                            }
    </script>
</html>
