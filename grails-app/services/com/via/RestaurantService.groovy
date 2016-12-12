package com.via

import grails.transaction.Transactional
import semester_project.Restaurant

@Transactional
class RestaurantService {

    static def getRestaurants(){
        return Restaurant.list()
    }
    static def findByZomatoID(int zomatoID){
        return Restaurant.findByZomatoId(zomatoID)
    }
    static def saveRestaurant(String name, String zomatoID){
        Restaurant rest = new Restaurant(zomatoId: Integer.parseInt(zomatoID), name: name);
        rest.save()
    }
}
