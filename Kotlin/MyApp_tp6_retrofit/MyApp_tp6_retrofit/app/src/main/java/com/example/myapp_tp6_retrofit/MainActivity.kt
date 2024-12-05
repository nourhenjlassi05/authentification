package com.example.myapp_tp6_retrofit

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit



import android.content.Intent


import android.view.View

import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    lateinit var resptoken :AccessToken
    lateinit var usernameT : EditText
    lateinit var paswordT : EditText
    lateinit var apitext : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val submit = findViewById<View>(R.id.submit) as MaterialButton
        usernameT = findViewById<EditText>(R.id.username)
        paswordT = findViewById<EditText>(R.id.password)
        apitext = findViewById<TextView>(R.id.apitext)


        val clientid ="androidapp"
        val grantTpe = "password"
        val clientSecret = "ri4IXSOeVNIiwDGmo3GmpCJMNR33FJtw"
        val kscope = "openid"


        val scope = CoroutineScope(Dispatchers.Main)
        var accessToken = ""


        submit.setOnClickListener{

            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch  {
                try {

                    val response =  ApiClient.login().getAccessToken(
                        clientid,
                        grantTpe,
                        clientSecret,
                        kscope,
                        usernameT.text.toString(),
                        paswordT.text.toString()
                    )

                    if (response.isSuccessful) {

                        if (response.body() != null)
                            println(response.body()?.accessToken.toString())
                        println(response.body()?.refreshToken.toString())
                        accessToken = response.body()?.accessToken.toString()

                    } else {
                        Toast.makeText(this@MainActivity, "Failed!", Toast.LENGTH_SHORT).show()
                    }

                    scope.launch  {
                        try {
                            val responseS = ApiClient.apiService.getcustomers("Bearer $accessToken")

                            if (responseS.isSuccessful && responseS.body() != null) {
                                apitext.text=responseS.body().toString()
                                Log.i("Success",responseS.body().toString())
                            } else {

                                Log.e("Error",responseS.headers().toString())
                            }

                        } catch (e: Exception) {
                            Log.e("Error",e.message.toString())
                            Toast.makeText(


                                this@MainActivity,
                                "Error Occurred: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    println("Exception: "+ex.message)
                }
            }
        }






    }
}