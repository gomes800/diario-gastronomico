package com.example.diario_gastronomico_app.repositories

import com.example.diario_gastronomico_app.domain.Restaurant
import com.example.diario_gastronomico_app.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepository {

    suspend fun getRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitClient.restaurantService.getRestaurants()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun getRestaurantById(id: Long): Restaurant? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitClient.restaurantService.getRestaurantById(id)
            } catch (e: Exception) {
                null
            }
        }
    }
}