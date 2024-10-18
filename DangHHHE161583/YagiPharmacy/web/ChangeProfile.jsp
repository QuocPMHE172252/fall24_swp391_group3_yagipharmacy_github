<%-- 
    Document   : ChangeProfile
    Created on : Sep 22, 2024, 5:12:43 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">


    <title>bs4 edit profile page - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            margin-top: 20px;
            background: #f8f8f8
        }
    </style>
</head>

<body>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <div class="container">
        <div class="row flex-lg-nowrap">
            <div class="col-12 col-lg-auto mb-3" style="width: 200px;">
                <div class="card p-3">
                    <div class="e-navlist e-navlist--active-bg">
                        <ul class="nav">
                            <li class="nav-item"><a class="nav-link px-2 active" href="#"><i
                                        class="fa fa-fw fa-bar-chart mr-1"></i><span>Overview</span></a></li>
                            <li class="nav-item"><a class="nav-link px-2"
                                    href="https://www.bootdey.com/snippets/view/bs4-crud-users" target="__blank"><i
                                        class="fa fa-fw fa-th mr-1"></i><span>CRUD</span></a></li>
                            <li class="nav-item"><a class="nav-link px-2"
                                    href="https://www.bootdey.com/snippets/view/bs4-edit-profile-page"
                                    target="__blank"><i class="fa fa-fw fa-cog mr-1"></i><span>Settings</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <form action="ChangeProfile" method="post" id="formChange">
                <div class="col">
                    <div class="row">
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="e-profile">
                                        <div class="row">
                                            <div class="col-12 col-sm-auto mb-3">
                                                <img src="${sessionScope.userAuth.userAvatar}" alt="" height="140px" width="140px" id="profile_image">
                                            </div>
                                            <div
                                                class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                                <div class="text-center text-sm-left mb-2 mb-sm-0">
                                                    <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">${sessionScope.userAuth.userName}</h4>
                                                    <p class="mb-0">${sessionScope.userAuth.userEmail}</p>
                                                    <div class="text-muted"><small>${sessionScope.userAuth.userPhone}</small></div>
                                                    <div class="mt-2">
                                                        <input class="btn btn-primary" type="file" onchange="convertImageToBase64(this)" accept="image/*">
                                                        <input type="hidden" name="base64_img" id="base64_img" value="${sessionScope.userAuth.userAvatar}">
                                                    </div>
                                                </div>
                                                <div class="text-center text-sm-right">
                                                    <span class="badge badge-secondary">administrator</span>
                                                    <div class="text-muted"><small>Joined 09 Dec 2017</small></div>
                                                </div>
                                            </div>
                                        </div>
                                        <ul class="nav nav-tabs">
                                            <li class="nav-item"><a href class="active nav-link">Settings</a></li>
                                        </ul>
                                        <div class="tab-content pt-3">
                                            <div class="tab-pane active">
                                                <form class="form" novalidate>
                                                    <div class="row">
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Họ và Tên</label>
                                                                        <input class="form-control" type="text" id="fullname" name="fullname" placeholder="Enter your fullname" value="${sessionScope.userAuth.userName}">
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Ngày sinh</label>
                                                                        <input class="form-control" type="date" id="dob"
                                                                            name="dob" placeholder="" value="${formattedDate}">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Tỉnh/Thành phố</label>
                                                                        <select class="form-control"
                                                                            name="user_province" id="user_province"
                                                                            onchange="getDistrictData()">
                                                                            <option value="none">Không chọn</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Quận/Huyện</label>
                                                                        <select class="form-control"
                                                                            name="user_district" id="user_district"
                                                                            onchange="getCommuneData()">
                                                                            <option value="none">Không chọn</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Xã/Phường</label>
                                                                        <select class="form-control" name="user_commune"
                                                                            id="user_commune">
                                                                            <option value="none">Không chọn</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label>Địa chỉ cụ thể</label>
                                                                        <input class="form-control" type="text"
                                                                            name="specific_address"
                                                                            id="specific_address"
                                                                            placeholder="Enter your fullname" value="">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                            </div>
                                            <div class="row">
                                                <div class="col d-flex justify-content-end">
                                                    <button class="btn btn-primary" type="button" onclick="submitValid()">Save
                                                        Changes</button>
                                                </div>
                                            </div>
            </form>
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
    <script>
        <c:if test="${change!=null&&change == true}">
            window.alert("Thay đổi thành công!");
            window.close();
        </c:if>
            <c:if test="${change!=null&&change == false}">
            window.alert("Thay đổi thất bại!");
        </c:if>
        function getDistrictData() {
            var selectedProvince = document.getElementById("user_province").value;
            fetch('https://provinces.open-api.vn/api/?depth=3')
                .then(response => response.json())
                .then(data => {
                    data.forEach((province, province_index) => {
                        if (province.name == selectedProvince) {
                            document.getElementById("user_district").innerHTML = '<option value="none">Không chọn</option>';
                            province.districts.forEach((district, district_index) => {
                                let optionDistrict = '<option value="' + district.name + '">' + district.name + '</option>';
                                document.getElementById("user_district").innerHTML += optionDistrict;
                            });
                        }
                    });
                })
                .catch(error => {
                    console.error('Lỗi khi gọi API:', error);
                });
        }
        function getCommuneData() {
            var selectedProvince = document.getElementById("user_province").value;
            var selectedDistrict = document.getElementById("user_district").value;
            fetch('https://provinces.open-api.vn/api/?depth=3')
                .then(response => response.json())
                .then(data => {
                    data.forEach((province, province_index) => {
                        if (province.name == selectedProvince) {
                            province.districts.forEach((district, district_index) => {
                                if (district.name == selectedDistrict) {
                                    document.getElementById("user_commune").innerHTML = '<option value="none">Không chọn</option>';
                                    district.wards.forEach((commune, commune_index) => {
                                        let optionCommune = '<option value="' + commune.name + '">' + commune.name + '</option>';
                                        document.getElementById("user_commune").innerHTML += optionCommune;
                                    });
                                }
                            });
                        }
                    });
                })
                .catch(error => {
                    console.error('Lỗi khi gọi API:', error);
                });
        }
        function convertImageToBase64(input) {
            const file = input.files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
                const base64String = reader.result;
                document.getElementById("profile_image").src = base64String;
                document.getElementById("base64_img").value = base64String;
                console.log(base64String);
            };
            reader.readAsDataURL(file);
        }
        function submitValid(){
            var dob = document.getElementById("dob").value;
            var name = document.getElementById("fullname").value;
            console.log(name);
            var check = true;
            if(!validateDateOfBirth(dob)){
                check = false;
                window.alert("Ngày tháng năm sinh không vượt qua hiện tại, không dưới 16 tuổi và quá 80 tuổi");
            }
            if(!validName(name)){
                check = false;
                window.alert("Tên không được để trống");
            }
            if(check){
                document.getElementById("formChange").submit();
            }
        }
        function validateDateOfBirth(dateOfBirth) {
                const today = new Date();
                const inputDate = new Date(dateOfBirth);
                return inputDate <= today&&(today.getFullYear()-inputDate.getFullYear()<=80)&&(today.getFullYear()-inputDate.getFullYear()>=16);
        }
        function validName(name){
            if(name==null||name.trim()==""){
                return false;
            }
            return true;
        }
        fetch('https://provinces.open-api.vn/api/?depth=3')
            .then(response => response.json())
            .then(data => {
                listProvinces = data;
                console.log(data);
                data.forEach((province, province_index) => {
                    let optionProvince = '<option value="' + province.name + '">' + province.name + '</option>';
                    document.getElementById("user_province").innerHTML += optionProvince;
                });
            })
            .catch(error => {
                console.error('Lỗi khi gọi API:', error);
            });
        
    </script>
</body>
</html>