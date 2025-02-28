package com.example.diario_gastronomico_app.domain

data class Restaurant(
    val  id: Long,
    val name: String,
    val address: String,
    val phone: String,
    val site: String
)