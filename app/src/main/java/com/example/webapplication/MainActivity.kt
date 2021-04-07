package com.example.webapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult: TextView = findViewById(R.id.tvResult)
        val btnGetAll: Button = findViewById(R.id.getAll)
        btnGetAll.setOnClickListener() {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getAll",
                null,
                Response.Listener { response ->
                    try {

                        var nameList:StringBuffer= StringBuffer()
                        for(i in 0 until response.length()) {
                            val objStudent: JSONObject = response.getJSONObject(i)
                            nameList.append(objStudent.getString("name") + "\n")
                        }
                        tvResult.setText(nameList)

                    } catch (e: JSONException) {
                        tvResult.setText(e.message)
                    }
                }
                , Response.ErrorListener { error ->
                    tvResult.setText(error.message)
                }
            )

            rq?.add(objRequest)
        }


        val btnSearch: Button = findViewById(R.id.search)
        btnSearch.setOnClickListener() {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getById?id=w222",
                null,
                Response.Listener { response ->
                    try {

                        var name:String
                        var programme:String
                        val objStudent: JSONObject = response
                        name=objStudent.getString("name")
                        programme=objStudent.getString("programme")

                        val tfName:TextView=findViewById(R.id.studentName)
                        tfName.setText(name)

                        val tfProgramme:TextView=findViewById(R.id.studentProgramme)
                        tfProgramme.setText(programme)

                    } catch (e: JSONException) {
                        tvResult.setText(e.message)
                    }
                }
                , Response.ErrorListener { error ->
                    tvResult.setText(error.message)
                }
            )

            rq?.add(objRequest)
        }

        val btnAdd: Button = findViewById(R.id.add)
        btnAdd.setOnClickListener() {

            val inputId:String=(findViewById<TextView>(R.id.studentId)).text.toString()
            val inputName:String=(findViewById<TextView>(R.id.studentName)).text.toString()
            val inputProgramme:String=(findViewById<TextView>(R.id.studentProgramme)).text.toString()

            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/add?id="+ inputId+"&name="+inputName+"&programme="+inputProgramme ,
                null,
                Response.Listener { response ->
                    try {

                        var name:String
                        var programme:String
                        val objStudent: JSONObject = response
                        name=objStudent.getString("name")
                        programme=objStudent.getString("programme")

                        val tfName:TextView=findViewById(R.id.studentName)
                        tfName.setText(name)

                        val tfProgramme:TextView=findViewById(R.id.studentProgramme)
                        tfProgramme.setText(programme)

                    } catch (e: JSONException) {
                        tvResult.setText(e.message)
                    }
                }
                , Response.ErrorListener { error ->
                    tvResult.setText(error.message)
                }
            )

            rq?.add(objRequest)
        }

    }
}