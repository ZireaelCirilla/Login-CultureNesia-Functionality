package com.zireael.loginpage_culturenesia

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton (private val context: Context) {

    val requestQueue: RequestQueue by lazy { Volley.newRequestQueue(context) }

}