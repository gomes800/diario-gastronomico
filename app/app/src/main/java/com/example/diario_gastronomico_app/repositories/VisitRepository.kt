package com.example.diario_gastronomico_app.repositories

import android.util.Log
import com.example.diario_gastronomico_app.domain.Visit
import com.example.diario_gastronomico_app.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VisitRepository {

    suspend fun getVisits(): List<Visit> {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitClient.visitService.getVisits()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}