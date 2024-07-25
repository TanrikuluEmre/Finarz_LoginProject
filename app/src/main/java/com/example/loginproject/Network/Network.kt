package com.example.loginproject.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://staging-api.cusp.link/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    internal val service: APIInterface = retrofit.create(APIInterface::class.java)
}