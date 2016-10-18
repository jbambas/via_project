package semester_project

import com.via.ZomatoService
import grails.converters.JSON

class MainController {
    //API key zomato : 4823d8ca1a0a9068fcd3794b0daa8ea6
    //API key giphy  : dc6zaTOxFJmzC   - https://github.com/Giphy/GiphyAPI
    def index() {
        [restaurantList:RestaurantService.getRestaurants()]
    }

    def restaurant(){
        if(params.id != null && Restaurant.findByZomatoId(Long.parseLong(params.id)) != null){
            def data = ZomatoService.getDailyMenu(Integer.parseInt(params.id))
            if(JSON.parse(data)?.code==400){
                return [exception: null, data: JSON.parse("[]")]
            }
            return [exception: null, data:JSON.parse(data).get("daily_menus").collect {(it.daily_menu.dishes)}]
        }else return [exception: new Exception("Restaurant was not found in database."), data: JSON.parse("[]")]
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
        //pridat do databaze restauraci podle id...
        def data = JSON.parse(ZomatoService.getRestaurantDetails(Integer.parseInt(params.id)))
        Restaurant restaurant = new Restaurant(name: data.name, zomatoId: data.id)
        restaurant.save()
        redirect action:"index"
    }
}
