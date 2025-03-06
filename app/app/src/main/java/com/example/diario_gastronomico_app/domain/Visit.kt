package com.example.diario_gastronomico_app.domain

import java.time.LocalDateTime

data class Visit(
    val id: Long,
    val user: User,
    val restaurant: Restaurant,
    val rating: Byte,
    val comment: String,
    val visitDate: String
)
