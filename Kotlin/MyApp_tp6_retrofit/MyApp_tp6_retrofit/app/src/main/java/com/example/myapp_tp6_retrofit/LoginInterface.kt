package com.example.myapp_tp6_retrofit


import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CompletableJob
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.Calendar
import java.util.Observable

interface LoginInterface {
    @FormUrlEncoded
    @POST("realms/ebank-realm/protocol/openid-connect/token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("client_secret") clientSecret: String,
        @Field("scope") scope: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<AccessToken>

    @FormUrlEncoded
    @POST("realms/ebank-realm/protocol/openid-connect/logout")
    suspend fun logout(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,

        ): Response<Unit>
}

