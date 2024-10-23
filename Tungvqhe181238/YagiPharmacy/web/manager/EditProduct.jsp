<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <title>Edit Product</title>
            <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
            <link rel="icon" href="./assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

            <!-- Fonts and icons -->
            <script src="./assets/js/plugin/webfont/webfont.min.js"></script>
            <script>
                WebFont.load({
                    google: { families: ["Public Sans:300,400,500,600,700"] },
                    custom: {
                        families: [
                            "Font Awesome 5 Solid",
                            "Font Awesome 5 Regular",
                            "Font Awesome 5 Brands",
                            "simple-line-icons",
                        ],
                        urls: ["./assets/css/fonts.min.css"],
                    },
                    active: function () {
                        sessionStorage.fonts = true;
                    },
                });
            </script>



            <!-- CSS Files -->
            <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
            <link rel="stylesheet" href="./assets/css/plugins.min.css" />
            <link rel="stylesheet" href="./assets/css/kaiadmin.min.css" />

            <!-- CSS Just for demo purpose, don't include it in your project -->
            <link rel="stylesheet" href="./assets/css/demo.css" />
        </head>

        <body>
            <div class="wrapper">
                <!-- Sidebar -->
                <jsp:include page="./sidebar.jsp" />
                <!-- End Sidebar -->

                <div class="main-panel">
                    <div class="main-header">
                        <div class="main-header-logo">
                            <!-- Logo Header -->
                            <div class="logo-header" data-background-color="dark">
                                <a href="index.html" class="logo">
                                    <img src="./assets/img/kaiadmin/logo_light.svg" alt="navbar brand"
                                        class="navbar-brand" height="20" />
                                </a>
                                <div class="nav-toggle">
                                    <button class="btn btn-toggle toggle-sidebar">
                                        <i class="gg-menu-right"></i>
                                    </button>
                                    <button class="btn btn-toggle sidenav-toggler">
                                        <i class="gg-menu-left"></i>
                                    </button>
                                </div>
                                <button class="topbar-toggler more">
                                    <i class="gg-more-vertical-alt"></i>
                                </button>
                            </div>
                            <!-- End Logo Header -->
                        </div>
                        <!-- Navbar Header -->
                        <jsp:include page="./header.jsp" />
                        <!-- End Navbar -->
                    </div>

                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h3>Edit Product</h3>
                                    </div>
                                    <div class="card-body">
                                        <form method="post" action="EditProduct" id="editProductForm" class="row">
                                            <input type="hidden" name="product_id" value="${product.productId}">
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_code">Product Code</label>
                                                <input type="text" class="form-control" name="product_code"
                                                    id="product_code" value="${product.productCode}"
                                                    placeholder="Enter product code" required>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_name">Product Name</label>
                                                <input type="text" class="form-control" name="product_name"
                                                    id="product_name" value="${product.productName}"
                                                    placeholder="Enter product name" required>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_category">Product Category</label>
                                                <select class="form-control" name="product_category"
                                                    id="product_category" required>
                                                    <c:forEach items="${productCategorys}" var="pc">
                                                        <option value="${pc.productCategoryId}"
                                                            ${product.productCategory.productCategoryId==pc.productCategoryId
                                                            ? 'selected' : '' }>${pc.productCategoryName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="dosage_form">Dosage Form</label>
                                                <input type="text" class="form-control" name="dosage_form"
                                                    id="dosage_form" value="${product.dosageForm}"
                                                    placeholder="Enter dosage form" required>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_specification">Product Specification</label>
                                                <input type="text" class="form-control" name="product_specification"
                                                    value="${product.productSpecification}" id="product_specification"
                                                    placeholder="Enter product specification" required>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_target">Product Target</label>
                                                <input type="text" class="form-control" name="product_target"
                                                    value="${product.productTarget}" id="product_target"
                                                    placeholder="Enter product target" required>
                                            </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_desciption">Additional Information</label>
                                                <textarea class="form-control" id="product_desciption"
                                                    name="product_desciption" rows="5"
                                                    cols="10">${product.productDescription}</textarea>
                                            </div>



                                            <div class="form-group mb-3 col-md-6"> <label
                                                    for="excipients">Excipients</label> <select class="form-control-sm"
                                                    type="text" id="excipients" name="excipients" required>

                                                    <c:forEach items="${excipientsData}" var="excipient">
                                                        <option value="${excipient.excipientId}">
                                                            ${excipient.excipientName}</option>
                                                    </c:forEach>
                                                </select> <button class="btn-dropdown btn-success" type="button"
                                                    onclick="addNewExcipient()">+</button><br>
                                                <table class="form-group table">
                                                    <tbody id="exs_in_drug">
                                                        <tr>
                                                            <th>Excipient</th>
                                                            <th>Quantity</th>
                                                            <th>Unit</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                        <c:forEach items="${product.productExcipients}" var="pe"
                                                            varStatus="loop">
                                                            <tr id="rowId_${loop.index}">
                                                                <td> <input class="form-control-sm" type="text"
                                                                        id="excipient_name_${loop.index}"
                                                                        name="excipient_name_${loop.index}"
                                                                        value="${pe.excipient.excipientName}" readonly
                                                                        required> <input class="form-control-sm"
                                                                        type="text" id="excipient_id_${loop.index}"
                                                                        name="excipient_id_${loop.index}"
                                                                        value="${pe.excipientId}" readonly hidden> </td>
                                                                <td> <input
                                                                        onchange="changeQuantity(this,${pe.excipientId})"
                                                                        type="number" class="form-control-sm" min="0.01"
                                                                        step="0.01" value="${pe.quantity}" required>
                                                                </td>
                                                                <td> <select
                                                                        onchange="changeUnitMeasurement(this,${pe.excipientId})"
                                                                        class="form-control-sm" required>
                                                                        <option value="gram"
                                                                            ${pe.unitMeasurement=='gram' ? 'selected'
                                                                            : '' }>gram</option>
                                                                        <option value="ml" ${pe.unitMeasurement=='ml'
                                                                            ? 'selected' : '' }>ml</option>
                                                                    </select> </td>
                                                                <td><button type="button" class="btn-close"
                                                                        value="${loop.index}"
                                                                        onclick="deleteRow(${loop.index},${pe.excipientId})"></button>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table> <input type="text" class="form-control"
                                                    name="excipients_string" value="${excipients_string}"
                                                    id="excipients_string" required readonly>
                                            </div>
                                            <!--  -->
                                            <div class="form-group  mb-3 col-md-6">
                                                <label>Supplier</label><br />
                                                <select class="form-control-sm" id="suplier_id" name="suplier_id"
                                                    required>
                                                    <c:forEach items="${suppliers}" var="supplier">
                                                        <option value="${supplier.supplierId}"
                                                            ${product.supplier.supplierId==supplier.supplierId
                                                            ? 'selected' : '' }>${supplier.supplierName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group  mb-3 col-md-6">
                                                <label>Country</label><br />
                                                <select class="form-control-sm" id="product_country_code"
                                                    name="product_country_code" required>
                                                    <option value="${product.productCountryCode}" selected>
                                                        ${product.productCountryCode}</option>
                                                </select>
                                            </div>
                                            <div class="form-group  mb-3 col-md-6" style="display: block !important;">
                                                <label>Prescription</label><br />
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" name="is_prescription"
                                                        id="is_prescription_yes" value="0" ${product.isPrescription
                                                        ? 'checked' : '' } />
                                                    <label class="form-check-label"
                                                        for="is_prescription_yes">Yes</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" name="is_prescription"
                                                        id="is_prescription_no" value="1" ${!product.isPrescription
                                                        ? 'checked' : '' } />
                                                    <label class="form-check-label" for="is_prescription_no">No</label>
                                                </div>
                                            </div>
                                            <div class="form-group mb-3 col-md-6"> <label for="units">Product
                                                    Units</label> <select class="form-control-sm" type="text" id="units"
                                                    name="units" required>
                                                </select> <button class="btn-dropdown btn-success" type="button"
                                                    onclick="addNewUnit()">+</button><br>
                                                <table class="form-group table">
                                                    <tbody id="units_in_drug">
                                                        <tr>
                                                            <th>Unit Name</th>
                                                            <th>Quantity Per Unit</th>
                                                            <th>Can Be Sold</th>
                                                            <th>Sell Price</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                        <c:forEach items="${product.productUnits}" var="pu"
                                                            varStatus="loop">
                                                            <tr id="rowIdUnit_${loop.index}">
                                                                <td>
                                                                    <input class="form-control-sm" type="text"
                                                                        id="unit_name_${loop.index}"
                                                                        name="unit_name_${loop.index}"
                                                                        value="${pu.unit.unitName}" readonly required>
                                                                    <input class="form-control-sm" type="text"
                                                                        id="unit_id_${loop.index}"
                                                                        name="unit_id_${loop.index}"
                                                                        value="${pu.unitId}" readonly hidden>
                                                                </td>
                                                                <td>
                                                                    <input
                                                                        onchange="changeQuantityPerUnit(this,${pu.unitId})"
                                                                        type="number" class="form-control-sm" min="1"
                                                                        step="1" value="${pu.quantityPerUnit}" required>
                                                                </td>
                                                                <td>
                                                                    <select
                                                                        onchange="changeCanBeSold(this,${pu.unitId})"
                                                                        class="form-control-sm" required>
                                                                        <option value="yes" ${pu.canBeSold ? 'selected'
                                                                            : '' }>yes</option>
                                                                        <option value="no" ${!pu.canBeSold ? 'selected'
                                                                            : '' }>no</option>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <input onchange="changePrice(this,${pu.unitId})"
                                                                        type="number" class="form-control-sm" min="0"
                                                                        step="0.01" value="${pu.sellPrice}" required
                                                                        placeholder="VND">
                                                                </td>
                                                                <td><button type="button" class="btn-close"
                                                                        value="${loop.index}"
                                                                        onclick="deleteRowUnit(${loop.index},${pu.unitId})"></button>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <input type="text" class="form-control" name="units_string"
                                                    value="${units_string}" id="units_string" required readonly>
                                            </div>

                                            <div class="form-group mb-3 col-md-6">
                                                <label for="product_image">Avatar</label>
                                                <input type="file" class="form-control" id="product_image"
                                                    accept="image/*" required>
                                                <input type="hidden" class="form-control" id="product_image_submit"
                                                    name="product_image_submit"
                                                    value="${product.productImages[0].imageBase64}">
                                            </div>
                                            <div class="form-group mb-3 col-md-6">
                                                <label for="date_of_birth">Preview</label>
                                                <img id="avatarPreview" class="mt-3"
                                                    src="data:image/jpeg;base64,${product.productImages[0].imageBase64}"
                                                    alt="Avatar Preview" style="max-width: 300px;">
                                            </div>

                                            <p>${errorMessage}</p>
                                            <div class="form-group mb-3 col-md-12">
                                                <button type="submit" class="btn btn-success" style="width: 100px"
                                                    onclick="submitForm()">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <footer class="footer">
                        <div class="container-fluid d-flex justify-content-between">
                            <nav class="pull-left">
                                <ul class="nav">
                                    <li class="nav-item">
                                        <a class="nav-link">
                                            Group 3
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#"> Help </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#"> Licenses </a>
                                    </li>
                                </ul>
                            </nav>
                            <div class="copyright">
                                2024, made with <i class="fa fa-heart heart text-danger"></i> by Group 3
                            </div>
                            <div>
                                Distributed by
                                <a target="_blank" href="">SWP391</a>.
                            </div>
                        </div>
                    </footer>
                </div>


            </div>


            <script>
                // Fetch country list and populate the dropdown
                fetch('https://restcountries.com/v3.1/all')
                    .then(response => response.json())
                    .then(data => {
                        data.forEach(country => {
                            //                            console.log(`Country: `+country.name.common+`), Code:`+ country.cca2);
                            document.getElementById("product_country_code").innerHTML += createCountryOption(country.name.common, country.cca2);
                        });
                    })
                    .catch(error => console.error('Error:', error));

                function createCountryOption(country_name, country_code) {
                    return `<option value="` + country_code + `">` + country_name + `(` + country_code + `)</option>`;
                }


                function ExcipientDetail(ex_id, quantity, uni_measurement) {
                    this.ex_id = ex_id;
                    this.quantity = quantity;
                    this.uni_measurement = uni_measurement;
                }

                function UnitDetail(unit_id, quantity_per_unit, can_be_sold, sell_price) {
                    this.unit_id = unit_id;
                    this.quantity_per_unit = quantity_per_unit;
                    this.can_be_sold = can_be_sold;
                    this.sell_price = sell_price;
                }

                var countNumber = 0;
                var countNumber_unit = 0;
                var listExcipientAdded = [];
                var listUnitAdded = [];

                const excipientsJson = '${excipientsJson}';
                const excipientsData = JSON.parse(excipientsJson);
                const unitsJson = '${unitsJson}';
                const unitsData = JSON.parse(unitsJson);

                // Initialize excipients dropdown
                excipientsData.forEach(function (excipient) {
                    document.getElementById("excipients").innerHTML += `<option value="` + excipient.excipientId + `">` + excipient.excipientName + `</option>`;
                });

                // Initialize units dropdown
                unitsData.forEach(function (unit) {
                    document.getElementById("units").innerHTML += `<option value="` + unit.unitId + `">` + unit.unitName + `</option>`;
                });

                // Initialize listExcipientAdded with existing excipients
                <c:forEach items="${product.productExcipients}" var="pe">
                    listExcipientAdded.push(new ExcipientDetail('${pe.excipientId}', ${pe.quantity}, '${pe.unitMeasurement}'));
                </c:forEach>
            
                // Initialize listUnitAdded with existing units
                <c:forEach items="${product.productUnits}" var="pu">
                    listUnitAdded.push(new UnitDetail('${pu.unitId}', ${pu.quantityPerUnit}, ${pu.canBeSold}, ${pu.sellPrice}));
                </c:forEach>

                function addNewExcipient() {
                    var excipients_tag = document.getElementById("excipients");
                    var ex_id = excipients_tag.value;
                    var ex_name = excipients_tag.options[excipients_tag.selectedIndex].text;
                    var newExcipientDetail = new ExcipientDetail(ex_id, 0.01, 'gram');
                    var check = true;

                    // Check if the excipient is already added dynamically
                    listExcipientAdded.forEach(function (excipientDetail) {
                        if (excipients_tag.value === excipientDetail.ex_id) {
                            check = false;
                        }
                    });

                    // Check if the excipient is already in the product
                    document.querySelectorAll("#exs_in_drug input[name^='excipient_id_']").forEach(function (input) {
                        if (input.value === ex_id) {
                            check = false;
                        }
                    });

                    if (check) {
                        countNumber++;
                        listExcipientAdded.push(newExcipientDetail);
                        var rowContent = createRow(countNumber, ex_id, ex_name);
                        var newRow = document.createElement('tr');
                        newRow.id = "rowId_" + countNumber;
                        newRow.innerHTML = rowContent;
                        document.getElementById("exs_in_drug").appendChild(newRow);
                        changeExcipientsString(); // Update excipients_string after adding new excipient
                        console.log("Added new excipient:", ex_name);
                    } else {
                        alert("This excipient is already added.");
                    }
                }

                function addNewUnit() { var units_tag = document.getElementById("units"); var unit_id = units_tag.value; var unit_name = units_tag.options[units_tag.selectedIndex].text; var newUnitDetail = new UnitDetail(unit_id, 1, false, 0); var check = true; listUnitAdded.forEach(function (unitDetail) { if (units_tag.value === unitDetail.unit_id) { check = false; } }); if (check) { countNumber_unit++; listUnitAdded.push(newUnitDetail); rowContent = creatRowUnit(countNumber_unit, unit_id, unit_name); var newRow = document.createElement('tr'); newRow.id = "rowIdUnit_" + countNumber_unit; newRow.innerHTML = rowContent; document.getElementById("units_in_drug").appendChild(newRow); console.log("Added new unit: " + unit_name); changeUnitsString(); } }

                function createRow(count_num, ex_id, ex_name) {
                    rowContent = `<tr id="rowId_` + count_num + `">
                                                    <td>
                                                        <input class="form-control-sm" type="text" id="excipient_name_` + count_num + `" name="excipient_name_` + count_num + `" value="` + ex_name + `" readonly required>
                                                        <input class="form-control-sm" type="text" id="excipient_id_` + count_num + `" name="excipient_id_` + count_num + `" value="` + ex_id + `" readonly hidden>
                                                    </td>
                                                    <td>
                                                        <input onchange="changeQuantity(this,` + ex_id + `)" type="number" class="form-control-sm" min="0.01" step="0.01" value="0.01" required>
                                                    </td>
                                                    <td>
                                                        <select onchange="changeUnitMeasurement(this,` + ex_id + `)" class="form-control-sm" required>
                                                            <option value="gram">gram</option>
                                                            <option value="ml">ml</option>
                                                        </select>
                                                    </td>
                                                    <td><button type="button" class="btn-close" value="` + count_num + `" onclick="deleteRow(` + count_num + `,` + ex_id + `)"></button></td>
                                                </tr>`;
                    return rowContent;
                }


                function changeQuantity(quantity_tag, ex_id) {
                    listExcipientAdded.forEach(function (excipientDetail) {
                        if (ex_id == excipientDetail.ex_id) {
                            if (quantity_tag.value !== '') {
                                excipientDetail.quantity = parseFloat(quantity_tag.value);
                            } else {
                                excipientDetail.quantity = 0.01;
                            }
                        }
                    });
                    changeExcipientsString();
                }

                function changeUnitMeasurement(measurement_tag, ex_id) {
                    listExcipientAdded.forEach(function (excipientDetail) {
                        if (ex_id == excipientDetail.ex_id) {
                            if (measurement_tag.value !== '') {
                                excipientDetail.uni_measurement = measurement_tag.value;
                            } else {
                                excipientDetail.uni_measurement = 'gram';
                            }
                        }
                    });
                    changeExcipientsString();
                }

                function creatRowUnit(count_num, unit_id, unit_name) {
                    rowContent = `<tr id="rowId_` + count_num + `">
                                                    <td>
                                                        <input class="form-control-sm" type="text" id="unit_name_` + count_num + `" name="unit_name_` + count_num + `" value="` + unit_name + `" readonly required>
                                                        <input class="form-control-sm" type="text" id="unit_id_` + count_num + `" name="unit_id_` + count_num + `" value="` + unit_id + `" readonly hidden>
                                                    </td>
                                                    <td>
                                                        <input onchange="changeQuantity(this,` + unit_id + `)" type="number" class="form-control-sm" min="0.01" step="0.01" value="0.01" required>
                                                    </td>
                                                    <td>
                                                        <select onchange="changeCanBeSold(this,` + unit_id + `)" class="form-control-sm" required>
                                                            <option value="no">no</option>
                                                            <option value="yes">yes</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input onchange="changePrice(this,` + unit_id + `)" type="number" class="form-control-sm" min="0" step="0.01" value="0" required placeholder = "VNĐ">
                                                    <td><button type="button" class="btn-close" value="` + count_num + `" onclick="deleteRowUnit(` + count_num + `,` + unit_id + `)"></button></td>
                                                </tr>`;
                    return rowContent;
                }

                function changeCanBeSold(sold_tag, unit_id) {
                    listUnitAdded.forEach(function (unitDetail) {
                        if (unit_id == unitDetail.unit_id) {
                            if (sold_tag.value !== '') {
                                unitDetail.can_be_sold = sold_tag.value == 'yes';
                            } else {
                                unitDetail.sell_price = 0;
                            }
                        }
                    });
                    changeUnitsString();
                }

                function deleteRowUnit(index, unit_id) {
                    document.getElementById("rowIdUnit_" + index).remove();
                    listUnitAdded = listUnitAdded.filter(function (unitDetail) {
                        return unitDetail.unit_id !== unit_id;
                    });
                    changeUnitsString();
                    console.log("Deleted unit with id:", unit_id);
                }

                function changeQuantityPerUnit(quantity_tag, unit_id) {
                    listUnitAdded.forEach(function (unitDetail) {
                        if (unit_id == unitDetail.unit_id) {
                            if (quantity_tag.value !== '') {
                                unitDetail.quantity_per_unit = parseFloat(quantity_tag.value);
                            } else {
                                unitDetail.quantity_per_unit = 0.01;
                            }
                        }
                    });
                    changeUnitsString();
                }


                function changeUnitsString() {
                    document.getElementById("units_string").value = "";
                    var valueOfArrString = "";
                    listUnitAdded.forEach(function (unitDetail) {
                        valueOfArrString += (unitDetail.unit_id + "_" + unitDetail.quantity_per_unit + "_" + unitDetail.can_be_sold + "_" + unitDetail.sell_price + ",");
                    });
                    if (valueOfArrString.length > 0) {
                        valueOfArrString = valueOfArrString.slice(0, -1);
                    }
                    document.getElementById("units_string").value = valueOfArrString;
                    console.log("Updated units_string: " + document.getElementById("units_string").value);
                }

                function changePrice(price_tag, unit_id) {
                    listUnitAdded.forEach(function (unitDetail) {
                        if (unit_id == unitDetail.unit_id) {
                            if (price_tag.value !== '') {
                                unitDetail.sell_price = parseFloat(price_tag.value);
                            } else {
                                unitDetail.sell_price = 0;
                            }
                        }
                    });
                    changeUnitsString();
                }

                function changeExcipientsString() {
                    var excipientsString = "";
                    listExcipientAdded.forEach(function (excipientDetail) {
                        excipientsString += excipientDetail.ex_id + "_" + excipientDetail.quantity + "_" + excipientDetail.uni_measurement + ",";
                    });
                    if (excipientsString.length > 0) {
                        excipientsString = excipientsString.slice(0, -1); // Remove the trailing comma
                    }
                    document.getElementById("excipients_string").value = excipientsString;
                    console.log("Updated excipients_string:", excipientsString);
                }

                function deleteRow(index, ex_id) {
                    document.getElementById("rowId_" + index).remove();
                    listExcipientAdded = listExcipientAdded.filter(function (excipientDetail) {
                        return excipientDetail.ex_id !== ex_id;
                    });
                    changeExcipientsString();
                    console.log("Deleted excipient with id:", ex_id);
                }

                function submitForm() {
                    document.getElementById("editProductForm").submit();
                }

                function validateAvatar(file) {
                    const validFileTypes = ['image/jpeg', 'image/png', 'image/gif'];
                    const maxSizeInBytes = 5 * 1024 * 1024; // 5 MB

                    if (!validFileTypes.includes(file.type)) {
                        alert('Invalid file type. Please select an image file (jpeg, png, gif).');
                        return false;
                    }

                    if (file.size > maxSizeInBytes) {
                        alert('File size exceeds the maximum limit of 5 MB.');
                        return false;
                    }

                    return true;
                }

                document.getElementById('product_image').addEventListener('change', function (event) {
                    const file = event.target.files[0];
                    if (!validateAvatar(file)) {
                        event.target.value = ''; // Clear the input
                        return;
                    } else {
                        const reader = new FileReader();
                        reader.onload = function (e) {
                            const base64Image = e.target.result;
                            document.getElementById('avatarPreview').src = base64Image;
                            document.getElementById('avatarPreview').style.display = 'block';
                            document.getElementById('product_image_submit').value = base64Image;
                        };
                        reader.readAsDataURL(file);
                    }
                });
            </script>

            <!--   Core JS Files   -->
            <script src="./assets/js/core/jquery-3.7.1.min.js"></script>
            <script src="./assets/js/core/popper.min.js"></script>
            <script src="./assets/js/core/bootstrap.min.js"></script>

            <!-- jQuery Scrollbar -->
            <script src="./assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

            <!-- Chart JS -->
            <script src="./assets/js/plugin/chart.js/chart.min.js"></script>

            <!-- jQuery Sparkline -->
            <script src="./assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

            <!-- Chart Circle -->
            <script src="./assets/js/plugin/chart-circle/circles.min.js"></script>

            <!-- Datatables -->
            <script src="./assets/js/plugin/datatables/datatables.min.js"></script>

            <!-- Bootstrap Notify -->
            <!--<script src="./assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>-->

            <!-- jQuery Vector Maps -->
            <script src="./assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
            <script src="./assets/js/plugin/jsvectormap/world.js"></script>

            <!-- Sweet Alert -->
            <script src="./assets/js/plugin/sweetalert/sweetalert.min.js"></script>

            <!-- Kaiadmin JS -->
            <script src="./assets/js/kaiadmin.min.js"></script>

            <!-- Kaiadmin DEMO methods, don't include it in your project! -->
            <script src="./assets/js/setting-demo.js"></script>
            <script src="./assets/js/demo.js"></script>
            <script>
                $("#lineChart").sparkline([102, 109, 120, 99, 110, 105, 115], {
                    type: "line",
                    height: "70",
                    width: "100%",
                    lineWidth: "2",
                    lineColor: "#177dff",
                    fillColor: "rgba(23, 125, 255, 0.14)",
                });
                $("#lineChart2").sparkline([99, 125, 122, 105, 110, 124, 115], {
                    type: "line",
                    height: "70",
                    width: "100%",
                    lineWidth: "2",
                    lineColor: "#f3545d",
                    fillColor: "rgba(243, 84, 93, .14)",
                });
                $("#lineChart3").sparkline([105, 103, 123, 100, 95, 105, 115], {
                    type: "line",
                    height: "70",
                    width: "100%",
                    lineWidth: "2",
                    lineColor: "#ffa534",
                    fillColor: "rgba(255, 165, 52, .14)",
                });
            </script>
            <script>
                $(document).ready(function () {
                    $('#tables').DataTable();
                }
                );
            </script>
        </body>

        </html>