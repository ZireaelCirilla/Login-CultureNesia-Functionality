package com.zireael.loginpage_culturenesia

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.zireael.loginpage_culturenesia.GlobalService.apiUrl
import org.json.JSONObject
import java.util.HashMap

class VolleyService(val context: Context) {

    fun login(email: String, password: String, callback: DataObjectCallback) {
        val params = HashMap<String, String>()
        params["email"] = email
        params["password"] = password
        val jsonObject = JSONObject(params)
        Log.i("EMAIL", email)
        var postRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST, "${apiUrl}/login", jsonObject,
            Response.Listener { response ->
                // response
                callback.onSuccess(response)
            },
            Response.ErrorListener { response ->
                // error
                Log.i("Error.Response", "$response")
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG)
                callback.onError(response)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()

                headers["Accept"] = "application/json"
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(10 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        VolleySingleton(context).requestQueue.add(postRequest)
    }

    fun getTest(callback: DataObjectCallback) {
        val request = object: JsonObjectRequest(
            Method.GET, "${apiUrl}/test", null,
            Response.Listener { response ->
                callback.onSuccess(response)
            },
            Response.ErrorListener { err ->
                println(err)
                callback.onError(err)
                Toast.makeText(context, "That didn't work!", Toast.LENGTH_SHORT).show()
            }) {
            /*override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()

                headers["Authorization"] = "Bearer ${GlobalService.session.access_token}"

                return headers
            }*/
        }
        VolleySingleton(context).requestQueue.add(request)
    }
    fun refreshToken(email: String, password: String, callback: DataObjectCallback) {
        val params = HashMap<String, String>()
        params["refresh_token"] = GlobalService.session["refresh_token"].toString()
        params["grant_type"] = "refresh_token"
        val jsonObject = JSONObject(params)
        Log.i("EMAIL", email)
        var refreshTokenRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST, "${apiUrl}/refresh-token", jsonObject,
            Response.Listener { response ->
                // response
                callback.onSuccess(response)
            },
            Response.ErrorListener { response ->
                // error
                Log.i("Error.Response", "$response")
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG)
                callback.onError(response)
            }
        ) {}
        refreshTokenRequest.retryPolicy = DefaultRetryPolicy(10 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        VolleySingleton(context).requestQueue.add(refreshTokenRequest)
    }
}