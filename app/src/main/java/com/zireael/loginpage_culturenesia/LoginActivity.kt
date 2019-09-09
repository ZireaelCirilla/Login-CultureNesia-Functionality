package com.zireael.loginpage_culturenesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.VolleyError
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.spinner_with_background.*
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_signin.setOnClickListener {
            spinner_darkened_background.visibility = View.VISIBLE
            VolleyService(applicationContext).login(et_email.text.toString(), et_password.text.toString(), object: DataObjectCallback{
                override fun onError(result: VolleyError) {
                    Toast.makeText(applicationContext, "Authentication Error", Toast.LENGTH_SHORT).show()
                    spinner_darkened_background.visibility = View.GONE
                }

                override fun onSuccess(result: JSONObject) {
                    GlobalService.session = result
                    val intent = Intent(applicationContext, MainActivity::class.java).setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK)
                    spinner_darkened_background.visibility = View.GONE
                    applicationContext.startActivity(intent)
                }
            })
        }
    }
}
