package semester_project

import grails.transaction.Transactional

@Transactional
class RestaurantService {

    static def getRestaurants(){
        return Restaurant.list()
    }
}
