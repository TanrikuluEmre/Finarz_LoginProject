package com.example.loginproject.Network

import com.example.loginproject.Network.response.LoginApiResponse
import com.example.loginproject.Network.response.SearchResultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


internal interface APIInterface {
    @POST("User/signin")
    fun loginUser(@Body credentials: LoginApiResponse): Call<SearchResultResponse>
}
