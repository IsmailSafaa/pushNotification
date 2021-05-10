package com.example.pushass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var firstname:String
    lateinit var secName:String
    lateinit var email:String
    lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstname = firstName.text.toString()
        email = Email.text.toString()
        password = passw.text.toString()
        secName = secondName.text.toString()
        signup.setOnClickListener {

            addUsers()

        }
        log.setOnClickListener {
            var i = Intent(this,MainActivity2::class.java)
            startActivity(i)
        }

    }

    private fun addUsers(){
        val url="https://mcc-users-api.herokuapp.com/add_new_user"
        val queue= Volley.newRequestQueue(this)
        val request = object : StringRequest(Request.Method.POST,url,
                Response.Listener { response ->
                    val data=  JSONObject(response)
                    Log.e("Add User","$data")
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
                }){
            override fun getParams(): MutableMap<String, String> {
                var params = HashMap<String, String>()
                params["firstName"] = firstName.text.toString()
                params["secondName"] = secondName.text.toString()
                params["email"] = Email.text.toString()
                params["password"] = passw.text.toString()
                // Log.e("Add User","$params")
                return params
            }

        }



        queue.add(request)

    }}