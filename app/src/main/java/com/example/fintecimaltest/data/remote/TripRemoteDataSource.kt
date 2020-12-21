package com.example.fintecimaltest.data.remote

import javax.inject.Inject

class TripRemoteDataSource @Inject constructor(
    private val tripService: TripService
): BaseDataSource(){

    suspend fun getTrips() = getResult { tripService.getAllTrips() }
}