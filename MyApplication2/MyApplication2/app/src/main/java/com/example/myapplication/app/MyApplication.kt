package com.example.myapplication.app

import android.app.Application

// this is system level application class responsible for all initializations
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}