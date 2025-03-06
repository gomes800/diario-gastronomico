package com.example.diario_gastronomico_app.network

import com.example.diario_gastronomico_app.domain.Visit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://675c-149-19-175-126.ngrok-free.app"

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