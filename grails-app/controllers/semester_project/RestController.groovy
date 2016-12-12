package semester_project

import com.via.RestaurantService
import com.via.ZomatoService
import grails.converters.JSON
import org.grails.web.json.JSONObject

class RestController {
    static allowedMethods = [   addRestaurant:'POST',
                                updateRestaurantName:'PUT',
                                deleteRestaurantById:'DELETE',
                                getRestaurants:'GET',
                                getRestaurantByZomatoID:'GET',
                                getLaunchMenu:'GET',
                                getRestaurantsByName:'GET']

    def addRestaurant(){//pridat do databaze....metoda post
        def name = params.name
        def zomatoID = params.zomatoID
        if (RestaurantService.findByZomatoID(zomatoID) == null){
            render(status: 201, text: zomatoID)
        } else if (name != null && zomatoID != null) {
            RestaurantService.saveRestaurant(name, zomatoID)
            render(status: 201, text: zomatoID)
        } else {
            render(status: 500, text: "No parameters sent.")
        }
    }
    def updateRestaurantName(){ //update jmena v databazi...metoda put
        def id = params.id
        def name = params.name
        Restaurant rest = RestaurantService.findByZomatoID(Integer.parseInt(id));
        rest?.name = name;
        rest?.save()
        render(status: 200)
    }
    def deleteRestaurantById(){ //odstraneni z databaze...metoda delete
        Restaurant rest = RestaurantService.findByZomatoID(params.id)
        rest?.delete()
        render(status: 204)
    }
    def getRestaurants(){//ziskani vsech zaznamu v databazi...metoda get
        def data = RestaurantService.getRestaurants()
        render data as JSON
    }
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    def getRestaurantByZomatoID(){//metoda get
        def id = params.id
        def data =  ZomatoService.getRestaurantDetails(Integer.parseInt(id))
        def json = new JSONObject()
        json.put("id", JSON.parse(data).id)
        json.put("name", JSON.parse(data).name)
        json.put("location", JSON.parse(data).location)

        render json as JSON
    }
    def getLaunchMenu(){//metoda get
        if(params.id != null){
            JSONObject json = new JSONObject(ZomatoService.getDailyMenu(Integer.parseInt(params.id)))
            if("success".equals(json.status))
                render json as JSON
            else render(status: 400)
        } else render(status: 404)
    }

    def getRestaurantsByName(){//metoda get
        if(request.getHeader("name") != null){
            JSONObject json = new JSONObject(ZomatoService.searchAllRestaurant(request.getHeader("name")))
            def data = json.restaurants?.collect{ rest-> [id:rest.restaurant.id, name:rest.restaurant.name, location:rest.restaurant.location] }
            render data as JSON
        } else render(status: 400, text: 'No restaurant name for search sent.')
    }

}
