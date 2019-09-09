package com.zireael.loginpage_culturenesia


import android.app.Application
import org.json.JSONObject

object GlobalService : Application() {
    lateinit var session: JSONObject
    const val apiUrl ="http://192.168.201.178:8080/api/v1"
}

