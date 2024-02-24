package com.example.myapplication.network

import com.example.myapplication.models.School
import retrofit2.http.GET


// https://data.cityofnewyork.us/resource/s3k6-pzi2.json

// this is my interface class which will contain all apis being used within this interface.
interface APIService {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): List<School>

}