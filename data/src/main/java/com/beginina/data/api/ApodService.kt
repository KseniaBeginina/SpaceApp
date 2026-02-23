package com.beginina.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {
    @GET("planetary/apod")
    suspend fun getApodRange(
        @Query("api_key") apiKey: String = "i5NTCdQnPgrURGNUdyLVnmR3aTiplV6Xtg8fPLq9",
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): List<ApodDto>
}