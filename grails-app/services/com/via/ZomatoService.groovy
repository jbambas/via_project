package com.via

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class ZomatoService {

    static final String APIKEY = "4823d8ca1a0a9068fcd3794b0daa8ea6"
    static final String url = "https://developers.zomato.com/api/v2.1/"
    static def getDailyMenu(int res_id) {
        def data = new RestBuilder().get(url+"dailymenu?res_id="+res_id){
            header 'user-key', APIKEY
            accept "application/json"
        }
        return data.body
    }

    static def searchRestaurant(String name, int pageNumber){
        def data = new RestBuilder().get(url+"search?entity_id=84&entity_type=city&q="+name+"&start="+(pageNumber*20)+"&sort=rating"){
            header 'user-key', APIKEY
            accept "application/json"
        }
        return data.body
    }

    static def getRestaurantDetails(int id){
        def data = new RestBuilder().get(url+"restaurant?res_id="+id){
            header 'user-key', APIKEY
            accept "application/json"
        }
        return data.body
    }
}
