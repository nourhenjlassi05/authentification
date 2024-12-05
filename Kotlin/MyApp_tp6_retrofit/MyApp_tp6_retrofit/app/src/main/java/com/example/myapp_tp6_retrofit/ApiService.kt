package com.example.myapp_tp6_retrofit



import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/customers/1")
    suspend fun getcustomers(
        @Header("Authorization") token: String

    ): Response<Customer>

}