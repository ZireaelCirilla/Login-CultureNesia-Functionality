package com.zireael.loginpage_culturenesia

import com.android.volley.VolleyError
import org.json.JSONObject

interface DataObjectCallback {
    fun onSuccess(result: JSONObject)
    fun onError(result: VolleyError)
}

interface DataStringCallBack {
    fun onSuccess(result: String)
    fun onError(result: VolleyError)
}