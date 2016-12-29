<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Restaurant ${restaurantData.name}</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><g:link controller='logout'>Logout</g:link></li>
    </ul>
</div>
<div class="container">
    <div class="col-md-6">
    <h1>Restaurant ${restaurantData.name}</h1>
    <g:if test="${exception!=null}">
        <p>${exception.message}</p>
    </g:if>
    <g:if test="${data==[]}">
        <p>This restaurant has no daily menu today.</p>
    </g:if>

    <ul class="list-group">
        <g:each in="${data}" var="item">
            <g:each in="${item}">
                <li class="list-group-item">${it.dish.name}  ${it.dish.price}</li>
            </g:each>
        </g:each>
    </ul>
    </div>

    <div class="col-md-6">
        <h3>${restaurantData.location.address} - Distance from inserted address: <g:if test="${distance == null}">----</g:if><g:else>${(double)Math.round(distance*10)/10}</g:else>km</h3>
        <div id="map" style="height: 400px; width: 100%"></div>
        <script type="text/javascript">
            function initMap() {
                var restaurant = {lat: ${restaurantData.location.latitude}, lng: ${restaurantData.location.longitude}};
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 16,
                    center: restaurant
                });
                var marker = new google.maps.Marker({
                    position: restaurant,
                    map: map
                });
            }
        </script>

    </div>
</div>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWK_cycbhsfuLn4nzHcDo5NkSgBTUEDKU&callback=initMap">
</script>
</body>
</html>