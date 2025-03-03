package com.example.diario_gastronomico_app.network

import com.example.diario_gastronomico_app.domain.Visit
import retrofit2.http.GET

interface VisitService {

    @GET("visits")
    suspend fun getVisits(): List<Visit>

}