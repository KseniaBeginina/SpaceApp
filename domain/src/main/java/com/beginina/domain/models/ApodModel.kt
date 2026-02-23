package com.beginina.domain.models

import java.time.LocalDate

data class ApodModel(
    val id: Int,
    val title: String,
    val url: String,
    val mediaType: String,
    val explanation: String,
    val date: LocalDate
)
