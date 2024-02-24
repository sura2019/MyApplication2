package com.example.myapplication.repository

import com.example.myapplication.models.School
import com.example.myapplication.network.APIService

// this is my repository class that will interact with network layer
class SchoolRepository(private val apiService: APIService) {

    suspend fun getSchools(): List<School>{
        return apiService.getSchools()
    }
}