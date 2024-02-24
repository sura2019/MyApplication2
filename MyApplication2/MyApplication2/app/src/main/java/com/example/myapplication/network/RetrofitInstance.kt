package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// this is my retrofit instance responsible to make network api call
object RetrofitInstance {
    private const val BASE_URL = "https://data.cityofnewyork.us/"

    val apiService:APIService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(APIService::class.java)
    }
}