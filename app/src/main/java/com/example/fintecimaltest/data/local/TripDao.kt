package com.example.fintecimaltest.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fintecimaltest.data.entities.Trip


@Dao
interface TripDao {

    @Query("SELECT * FROM trips")
    fun getAllTrips() : LiveData<List<Trip>>

    @Query("SELECT * FROM trips WHERE id = :id")
    fun getTrip(id: Int): LiveData<Trip>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(trips: List<Trip>)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(trip: Trip)


}
