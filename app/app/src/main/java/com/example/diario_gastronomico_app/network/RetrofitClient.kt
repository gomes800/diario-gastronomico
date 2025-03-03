package com.example.diario_gastronomico_app.network

import com.example.diario_gastronomico_app.domain.Visit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.158:8080/"

    private fun <T> createService(service: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
    }

    val restaurantService: RestaurantService by lazy {
        createService(RestaurantService::class.java)
    }

    val visitService: VisitService by lazy {
        createService(VisitService::class.java)
    }

    val userService: UserService by lazy {
        createService(UserService::class.java)
    }
}