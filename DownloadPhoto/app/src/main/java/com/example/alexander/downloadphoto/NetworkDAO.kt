package com.example.alexander.downloadphoto

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.impl.client.DefaultHttpClient
import java.io.IOException

/**
 * Created by Alexander on 18.08.2016.
 */
class NetworkDAO {
    @Throws(IOException::class)
    fun request(uri: String): String {
        // Use the GET method, which submits the search terms in the URL.
        val httpGet = HttpGet(uri)
        // how to handle response data.
        val responseHander = BasicResponseHandler()

        // marry the request and the response.
        val httpClient = DefaultHttpClient()
        var returnString: String? = null
        returnString = httpClient.execute(httpGet, responseHander)
        return returnString
    }
}