package com.example.androidcourse.network

import android.util.Log
import com.example.androidcourse.model.Product
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.formation-android.fr")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NetworkI::class.java)

    fun getProductAsync(barcode: String): Deferred<ServerResponse> {
        Log.d("ProductDetailsViewModel", "getProductAsync: $barcode")
        return api.getProductAsync(barcode)
    }
}