package com.example.diario_gastronomico_app.domain

import java.time.LocalDateTime

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val createDate: LocalDateTime,
    val visits: List<Visit>
)
