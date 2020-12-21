package com.example.fintecimaltest.ui.trips


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintecimaltest.data.repository.TripRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TripsViewModel @ViewModelInject constructor(
    private val repository: TripRepository
) : ViewModel() {

    val trips = repository.getTrips()
}