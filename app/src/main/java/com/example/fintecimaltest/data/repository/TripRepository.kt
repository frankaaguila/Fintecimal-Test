package com.example.fintecimaltest.data.repository

import com.example.fintecimaltest.data.local.TripDao
import com.example.fintecimaltest.data.remote.TripRemoteDataSource
import com.example.fintecimaltest.data.utils.performGetOperation
import javax.inject.Inject

class TripRepository @Inject constructor(
    private val remoteDataSource: TripRemoteDataSource,
    private val localDataSource: TripDao
) {

    fun getTrip(id: Int) = localDataSource.getTrip(id)

    //TODO: Actualizar status

    fun getTrips() = performGetOperation(
        databaseQuery = { localDataSource.getAllTrips() },
        networkCall = { remoteDataSource.getTrips() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}