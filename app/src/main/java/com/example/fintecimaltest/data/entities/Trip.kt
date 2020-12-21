package com.example.fintecimaltest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val streetName: String,
    val suburb: String,
    val visited: Boolean,
    val location: String
    )