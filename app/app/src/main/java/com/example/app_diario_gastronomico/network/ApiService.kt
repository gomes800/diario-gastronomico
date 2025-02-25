package com.example.app_diario_gastronomico.network

import retrofit2.http.GET
import retrofit2.http.Path

interface  ApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): List<Restaurant>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: Long): Restaurant
}