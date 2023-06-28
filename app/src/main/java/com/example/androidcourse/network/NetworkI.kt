package com.example.androidcourse.network

import com.example.androidcourse.model.Product
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkI {
    @GET("/product")
    suspend fun getProduct(@Query("barcode") barcode: String): Deferred<Product>
}