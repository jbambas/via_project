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

<div id="list-restaurant" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="col-md-6">
        <g:form controller="main" action="searchRestaurant">
            <div class="input-group">
            <input id="searchName" name="searchName" required="required" type="text" class="form-control" placeholder="Restaurant name:">
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
        //todo giphy
    </div>

</div>

</body>
</html>