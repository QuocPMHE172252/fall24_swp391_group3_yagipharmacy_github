<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- Added fn taglib -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>News Detail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
            }
            /* Header Menu Styling */
            .navbar {
                margin-bottom: 30px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            /* News Detail Styling */
            .news-detail {
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }

            .news-title {
                font-weight: bold;
                font-size: 2rem;
            }

            .news-meta {
                color: #6c757d;
                font-size: 0.9rem;
            }

            .news-content {
                margin-top: 20px;
                font-size: 1.1rem;
                line-height: 1.6;
            }

            .news-hashtag {
                color: #007bff;
            }

            .news-hashtag a {
                text-decoration: none;
            }

            .news-image {
                max-width: 100%;
                height: auto;
                border-radius: 10px;
                margin-bottom: 20px;
            }

            .container {
                margin-top: 30px;
            }

            footer {
                margin-top: 50px;
                padding: 20px;
                background-color: #333;
                color: #fff;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <!-- Header Menu -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">News Portal</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="newsList.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Categories</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact Us</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- News Detail Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="news-detail">
                        <!-- News Title -->
                        <h1 class="news-title">${neww.newsTitle}</h1>

                        <!-- Meta Information -->
                        <div class="news-meta mb-3">
                            <span>Created by User ID: ${neww.creatorId}</span> |
                            <span>Category: ${neww.category.newsCategoryName}</span> |
                            <span>Created on: ${neww.createdDate}</span>
                        </div>

                        <!-- News Image -->
                        <img style="margin-left: 25%" src="${neww.newsImage}" alt="News Image" class="news-image img-fluid">

                        <!-- News Content -->
                        <div class="news-content">
                            ${neww.newsContent}
                        </div>

                        <!-- News Hashtags -->
                        <div class="mt-4">
                            <span class="news-hashtag">Hashtags:</span>
                            <span class="news-hashtag">
                                <c:forEach var="hashtag" items="${fn:split(neww.newsHashtag, ',')}">
                                    <a href="#">#${hashtag.trim()}</a>
                                </c:forEach>
                            </span>
                        </div>

                        <!-- Back Button -->
                        <div class="mt-5">
                            <a href="newsList.jsp" class="btn btn-primary">Back to News List</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer>
            &copy; 2024 News Portal. All rights reserved.
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
