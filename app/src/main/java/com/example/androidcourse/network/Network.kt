package com.example.androidcourse.network

import com.example.androidcourse.model.Product
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.formation-android.fr/getProduct/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NetworkI::class.java)

    suspend fun getProduct(): Product {
        return api.getProduct("5000159484695").await()
    }
}