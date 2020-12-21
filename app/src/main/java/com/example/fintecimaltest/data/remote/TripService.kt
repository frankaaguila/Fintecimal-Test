package com.example.fintecimaltest.data.remote

import com.example.fintecimaltest.data.entities.TripList
import retrofit2.Response
import retrofit2.http.GET

interface TripService {
    @GET("interview")
    suspend fun getAllTrips(): Response<TripList>
}