package com.beginina.data.mapper

import com.beginina.data.api.ApodDto
import com.beginina.data.database.ApodEntity
import com.beginina.domain.models.ApodModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ApodDto.toDomain(): ApodModel{
    val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd") )
    val id = localDate.format(DateTimeFormatter.ofPattern("ddMMyyyy")).toInt()

    return ApodModel(
        id = id,
        title = title,
        url = hdurl ?: url,
        mediaType = mediaType,
        explanation = explanation,
        date = localDate
    )
}

fun ApodModel.toEntity() = ApodEntity(
    id = id,
    date = date.toString(),
    title = title,
    url = url,
    mediaType = mediaType,
    explanation = explanation
)

fun ApodEntity.toModel() = ApodModel(
    id = id,
    date = LocalDate.parse(date),
    title = title,
    url = url,
    mediaType = mediaType,
    explanation = explanation
)