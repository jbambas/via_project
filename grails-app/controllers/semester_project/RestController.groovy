package semester_project

import com.via.GoogleService
import com.via.RestaurantService
import com.via.ZomatoService
import grails.converters.JSON
import org.grails.web.json.JSONObject

class RestController {
    static allowedMethods = [   addRestaurant:['POST', 'OPTIONS'],
                                updateRestaurantName:['PUT', 'OPTIONS'],
                                deleteRestaurantById:['DELETE', 'OPTIONS'],
                                getRestaurants:['GET', 'OPTIONS'],
                                getRestaurantByZomatoID:['GET', 'OPTIONS'],
                                getLaunchMenu:['GET', 'OPTIONS'],
                                getRestaurantsByName:['GET', 'OPTIONS']]

    def addRestaurant(){
        def name = params.name
        def zomatoID = params.id
        if (RestaurantService.findByZomatoID(Integer.parseInt(zomatoID)) == null){
            RestaurantService.saveRestaurant(name, zomatoID)
            render(status: 201, text: new JSONObject().put("id", zomatoID))
        } else if (name != null && zomatoID != null) {
            render(status: 201, text: new JSONObject().put("id", zomatoID))
        } else {
            render(status: 500, text: "No parameters sent.")
        }
    }
    def updateRestaurantName(){
        def id = params.id
        def name = params.name
        if(id == null)render (status: 404)
        Restaurant rest = RestaurantService.findByZomatoID(Integer.parseInt(id))
        if (rest == null){
            rest = new Restaurant(name: name, zomatoId: id)
        }
        rest.name = name
        rest.save()
        render(status: 200, text: new JSONObject().put("id", id))
    }
    def deleteRestaurantById(){ //odstraneni z databaze...metoda delete
        if(params.id == null) render(status: 204)
        Restaurant rest = RestaurantService.findByZomatoID(Integer.parseInt(params.id))
        rest?.delete()
        render(status: 204)
    }
    def getRestaurants(){
        def data = RestaurantService.getRestaurants()
        render data as JSON
    }
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    def getRestaurantByZomatoID(){
        def id = params.id
        try {
            def data =  ZomatoService.getRestaurantDetails(Integer.parseInt(id))
            def json = new JSONObject()
            json.put("id", JSON.parse(data).id)
            json.put("name", JSON.parse(data).name)
            json.put("location", JSON.parse(data).location)
            if(params.address != null){
                def location = GoogleService.getLocation(params.address)
                if(location != null){
                    json.put("distance", GoogleService.getDistance(location.lat, location.lon,
                            Double.parseDouble(JSON.parse(data).location.latitude),
                            Double.parseDouble(JSON.parse(data).location.longitude), "K"))
                } else json.put("distance", "Inserted_address_not_found")
            } else {
                json.put("distance", "---")
            }
            render json as JSON
        } catch (Exception e){
            render (status: 404)
            return
        }
    }
    def getLaunchMenu(){
        if(params.id != null){
            try {
                JSONObject json = new JSONObject(ZomatoService.getDailyMenu(Integer.parseInt(params.id)))
                if("success".equals(json.status)) {
                    def menus = (json.daily_menus.collect {
                        it.daily_menu.dishes.collect { wrapper -> [price: wrapper.dish.price, name: wrapper.dish.name] }
                    })
                    def list = new ArrayList()
                    menus.each {dish -> list.addAll(dish)}
                    render new JSONObject().put("status", "success").put("dishes", list) as JSON
                } else {
                    JSONObject object = new JSONObject()
                    object.put("status", "success")
                    object.put("dishes", [])
                    render object as JSON
                }
            } catch (Exception e) {render (status: 404)}
        } else render(status: 404)
    }

    def getRestaurantsByName(){
        if(params.name != null){
            JSONObject json = new JSONObject(ZomatoService.searchAllRestaurant(params.name))
            def data = json.restaurants?.collect{ rest-> [id:rest.restaurant.id, name:rest.restaurant.name, location:rest.restaurant.location] }
            render data as JSON
        } else render(status: 400, text: 'No restaurant name for search sent.')
    }

}
