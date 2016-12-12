<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'restaurant.label', default: 'Restaurant')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.4.5/css/bootstrapValidator.min.css" />
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.4.5/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/bootstrap.min.js"></script>

</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" controller="restaurant" action="index">Manage restaurants</g:link></li>
    </ul>
</div>

<div id="list-restaurant" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="col-md-6">
        <g:form controller="main" action="searchRestaurant">
            <div class="input-group">
            <input id="searchName" name="searchName" required="required" type="text" class="form-control" placeholder="Search restaurant on Zomato:">
            <span class="input-group-btn">
                <button class="btn btn-default" type="submit">Search</button>
            </span>
            </div>
        </g:form>

        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Zomato ID</th>
                <th>Lunch menu</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${restaurantList}">
                <tr>
                <td>${it.name}</td>
                <td>${it.zomatoId}</td>
                <td><g:link controller="main" action="restaurant" id="${it.zomatoId}"><button type="button" class="btn btn-success">View Lunch Menu</button></g:link></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="col-md-6">
        <div class="col-lg-offset-3" id="locationDiv">
        </div>
    </div>

</div>

<script type="text/javascript">
    window.onload = function () {
        if(getCookie('address')==""){
            renderForm();
        } else {
            viewAddress();
        }
    }
    function renderForm() {
        $('#locationDiv').empty();
        $('#locationDiv').append('<h3>Put your location - address.</h3>');
        $('#locationDiv').append('<input id="street" required="required" type="text" class="form-control" placeholder="Street">');
        $('#locationDiv').append('<button onclick="saveAddress()">Set location</button>');
    }
    function viewAddress() {
        $('#locationDiv').empty();
        var address = getCookie("address");
        $('#locationDiv').append('<h3>Inserted address:</h3>');
        $('#locationDiv').append('<p>'+decodeURI(address)+'</p>');
        $('#locationDiv').append('<button onclick="renderForm()">Edit location</button>')
    }
    function saveAddress(){
        var address = encodeURI($('#street').val());
        setCookie("address", address.split(' ').join('+'));
        $('#locationDiv').empty();
        viewAddress();
    }
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+ d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length,c.length);
            }
        }
        return "";
    }
</script>
</body>
</html>