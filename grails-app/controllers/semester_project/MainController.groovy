package semester_project

import com.via.GoogleService
import com.via.RestaurantService
import com.via.ZomatoService
import grails.converters.JSON
import org.grails.web.json.JSONObject

class MainController {
    //API key zomato : 4823d8ca1a0a9068fcd3794b0daa8ea6
    //API key google maps:  AIzaSyAWK_cycbhsfuLn4nzHcDo5NkSgBTUEDKU
    def index() {
        [restaurantList:RestaurantService.getRestaurants()]
    }

    def restaurant(){
        if(params.id != null && RestaurantService.findByZomatoID(Integer.parseInt(params.id)) != null){
            def data = ZomatoService.getDailyMenu(Integer.parseInt(params.id))
            def restaurantData = ZomatoService.getRestaurantDetails(Integer.parseInt(params.id))
            def coordination = GoogleService.getLocation(java.net.URLDecoder.decode(request.cookies.find {it.name=='address'}?.value))
            def lat = Double.parseDouble(new JSONObject(restaurantData).location.latitude)
            def lon = Double.parseDouble(new JSONObject(restaurantData).location.longitude)
            def distance = null
            if (coordination != null)
                distance = GoogleService.getDistance(lat, lon, coordination.lat, coordination.lon, "K")
            if(JSON.parse(data)?.code==400){
                return [exception: null, data: JSON.parse("[]"), restaurantData: JSON.parse(restaurantData), distance: distance]
            }
            return [exception: null, data:JSON.parse(data).get("daily_menus").collect {(it.daily_menu.dishes)},
                    restaurantData: JSON.parse(restaurantData), distance:distance]
        }else return [exception: new Exception("Restaurant was not found in database."), data: JSON.parse("[]"),
                      restaurantData: JSON.parse("{}"), distance: 0]
    }

    def searchRestaurant(){
        int pageNumber = 0;
        if(params.pageNumber != null){
            pageNumber = Integer.parseInt(params.pageNumber)
        }
        if(params.searchName != null) {
            def data = ZomatoService.searchRestaurant(params.searchName, pageNumber)
            [exception: null, restaurantList: JSON.parse(data)]
        } else [exception: new Exception("No parameters to search."), data: JSON.parse("[]")]
    }
    def addRestaurant(){
        def data = JSON.parse(ZomatoService.getRestaurantDetails(Integer.parseInt(params.id)))
        RestaurantService.saveRestaurant(data.name, data.id)
        redirect action:"index"
    }
}
