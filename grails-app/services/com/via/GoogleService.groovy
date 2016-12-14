package com.via

import grails.plugins.rest.client.RestBuilder
import org.grails.web.json.JSONObject


class GoogleService {

    static final def APIKEY = "AIzaSyAWK_cycbhsfuLn4nzHcDo5NkSgBTUEDKU"
    static final def url = "https://maps.googleapis.com/maps/api/geocode/json"

    static def getLocation(String address) {
        if(address!=null || address.equals("")){
            def data = new RestBuilder().get(url+"?address="+address.replace('+', ' ')+"&key="+APIKEY).body
            data = new JSONObject(data)
            if("OK".equals(data.status)){
                return [lat: data.results[0].geometry.location.lat, lon:data.results[0].geometry.location.lng]
            } else return null
        } else return null
    }

    static def getDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts decimal degrees to radians		     :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts radians to decimal degrees			 :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
