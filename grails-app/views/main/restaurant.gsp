<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Restaurant</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<div class="container">
    <g:if test="${exception!=null}">
        <p>${exception.message}</p>
    </g:if>
    <g:each in="${data}" var="item">
        <g:each in="${item}">
            <p>${it.dish.name}  ${it.dish.price}</p>
        </g:each>
    </g:each>
</div>
</body>
</html>