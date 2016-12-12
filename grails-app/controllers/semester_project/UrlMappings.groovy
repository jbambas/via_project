package semester_project

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "main")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/api/restaurant"(controller: "rest", action: "getRestaurants")
        "/api/restaurant/$id"(controller: "rest", action: "getRestaurantByZomatoID")
        "/api/addrestaurant"(controller: "rest", action: "addRestaurant")
        "/api/updaterestaurant/$id"(controller: "rest", action: "updateRestaurantName")
        "/api/deleterestaurant/$id"(controller: "rest", action: "deleteRestaurantById")
        "/api/menu/$id"(controller: "rest", action: "getLaunchMenu")
        "/api/findrestaurants"(controller: "rest", action: "getRestaurantsByName")
    }
}
