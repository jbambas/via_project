<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Restaurant</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<div class="container">
    <g:if test="${exception != null}">
        <p>${exception.message}</p>
    </g:if>
    <g:else>
    <div class="col-md-6">
        <p>Restaurants found: ${restaurantList.results_found}</p>
        <p>Shown ${restaurantList.results_start} - ${(restaurantList.results_start as Integer) + (restaurantList.results_shown as Integer)}</p>
    </div>
        <div class="col-md-6 top-buffer">
            <g:link controller="main" action="index"><button class="btn btn-success col-lg-offset-8">Back</button></g:link>
        </div>
    <table>
        <thead>
            <tr>
                <th>Restaurant name</th>
                <th>Rating</th>
                <th>Address</th>
                <th>Add to the list</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${restaurantList.restaurants}">
                <tr>
                    <td>${it.restaurant.name}</td>
                    <td>${it.restaurant.user_rating.aggregate_rating}</td>
                    <td>${it.restaurant.location.address}</td>
                    <td>
                        <g:link controller="main" action="addRestaurant" params="[id:it.restaurant.id]">
                            <button class="btn btn-success">Add to the list</button>
                        </g:link>
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
<div class="text-center">
    <ul class="pagination">
        <g:if test="${params.pageNumber==null || params.pageNumber=='0'}">
            <g:if test="${(restaurantList.results_found as Integer) > ((restaurantList.results_start as Integer) + (restaurantList.results_shown as Integer))}">
            <li><a href="?pageNumber=${1}&searchName=${params.searchName}" data-original-title="" title="">Next</a></li>
            </g:if>
        </g:if>
        <g:else>
                <li><a href="?pageNumber=${(params.pageNumber as Integer)-1}&searchName=${params.searchName}" data-original-title="" title="">Previous</a></li>
            <g:if test="${(restaurantList.results_found as Integer)> ((restaurantList.results_start as Integer) + (restaurantList.results_shown as Integer))}">
                <g:if test="${(restaurantList.results_found as Integer) > ((restaurantList.results_start as Integer) + (restaurantList.results_shown as Integer))}">
                <li><a href="?pageNumber=${(params.pageNumber as Integer)+1}&searchName=${params.searchName}" data-original-title="" title="">Next</a></li>
                </g:if>
            </g:if>
        </g:else>
    </ul>

    </g:else>
</div>
</body>
</html>