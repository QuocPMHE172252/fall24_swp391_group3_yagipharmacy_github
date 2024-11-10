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
                <form action="PublishNew" method="post" id="formNewPublish">
                    <div class="col">
                        <div class="row">
                            <div class="col mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="e-profile">
                                            <div class="row">
                                                <div class="col-12 col-sm-auto mb-3" style="display: flex; align-content: center; align-items: center; flex-direction: column; width: 100%">
                                                    <h2>${findingNews.newsTitle}</h2>
                                                    <img src="${findingNews.newsImage}"  alt="" height="450px" id="news_image">
                                                    <div style="font-size: 18px;padding-top: 15px">Category: ${findingNews.newsCategory.newsCategoryName}</div>
                                                </div>

                                            </div>

                                            <ul class="nav nav-tabs">
                                                <li class="nav-item"><a href class="active nav-link">Reivew Post</a></li>
                                            </ul>
                                            <div class="tab-content pt-3">
                                                <div class="tab-pane active">
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Bài viết</label>
                                                                        <input type="hidden" readonly="" name="newsId" value="${findingNews.newsId}">
                                                                        <div style="border: 1px solid black; padding: 10px; min-height: 200px">
                                                                            ${findingNews.newsContent}
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <br>
                                                <div class="row">
                                                    <label>
                                                        Lý do từ chối:
                                                    </label>
                                                    <input id="rejectReason" type="text" value="${findingNews.rejectedReason}" name="rejectReason" class="form-control" >
                                                    <input id="atuhorId" type="hidden" value="${findingNews.creatorId}" name="atuhorId" class="form-control" >
                                                    <label style="margin-top: 5px;">
                                                        Trạng thái kiểm duyệt: ${findingNews.getRejectName()}
                                                    </label>
                                                </div>
                                                <div class="row">
                                                    <div class="mt-5">
                                                        <a href="../staff/ListNews" class="btn btn-primary">Back to News List</a>
                                                    </div>
                                                    <div class="col d-flex justify-content-end">
                                                        <input type="text" name="reject" value="false" id="is_rejecting" hidden="">
                                                        <button onclick="submitApprove(3)" class="btn btn-danger" style="margin-right: 10px" type="button"> Reject</button>
                                                        <button onclick="submitApprove(2)" class="btn btn-success" type="button">Approve</button>
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
                                                            window.alert("Cập nhật thành công");
        </c:if>
            function submitApprove(is_reject){
                document.getElementById("is_rejecting").value = is_reject;
                if(!is_reject){
                    document.getElementById("rejectReason").value ='';
                }
                
                document.getElementById("formNewPublish").submit();
            }
    </script>
</html>
