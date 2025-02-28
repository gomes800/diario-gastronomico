package com.example.diario_gastronomico_app.network

import com.example.diario_gastronomico_app.domain.Restaurant
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): List<Restaurant>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: Long): Restaurant
}