package com.via

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class GiphyService {

    static final def APIKEY = "dc6zaTOxFJmzC"
    static final def url = "http://api.giphy.com/v1/gifs/search?q="

    static def getImages() {
        def data = new RestBuilder().get(url+"food&api_key="+APIKEY)
        return data
    }
}
