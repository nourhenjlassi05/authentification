package com.example.myapp_tp6_retrofit


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // private const val BASE_URL: String = "https://jsonkeeper.com"
    // private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"
    private const val BASE_URL: String = "http://192.168.1.12:8086"
    private const val BASE_URLservice: String = "http://192.168.1.12:8083"


    fun login(): LoginInterface {
        return retrofitforauth.create(LoginInterface::class.java)
    }
    fun logout(): LoginInterface {
        return retrofitforauth.create(LoginInterface::class.java)
    }
    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofitforauth : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val retrofitforservice : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URLservice)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    val apiService :  ApiService by lazy{
        retrofitforservice.create(ApiService::class.java)
    }
}