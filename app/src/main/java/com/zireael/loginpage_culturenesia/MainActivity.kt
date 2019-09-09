package com.zireael.loginpage_culturenesia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.VolleyError
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VolleyService(this).getTest(object : DataObjectCallback {
            override fun onError(result: VolleyError) {
                println(result)
                tv_main.text = result.toString()
                /*if ()*/
            }

            override fun onSuccess(result: JSONObject) {
                println(result)
                tv_main.text = result.toString()
            }

        })
    }
}
