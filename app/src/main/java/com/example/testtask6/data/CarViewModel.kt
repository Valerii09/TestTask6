package com.example.testtask6.data

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CarViewModel(private val carRepository: CarRepository) : ViewModel() {
    private val searchQuery = MutableStateFlow("")

    val cars = combine(searchQuery, carRepository.searchCars("")) { query, cars ->
        if (query.isBlank()) {
            cars
        } else {
            cars.filter { car -> car.name.contains(query, ignoreCase = true) }
        }
    }.asLiveData()

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    suspend fun addCar(car: CarEntity) {
        carRepository.insertCar(car)
    }
    fun saveCarInfo(car: CarEntity) {
        viewModelScope.launch {
            carRepository.insertCar(car)
        }
    }

    // Другие методы для работы с данными автомобилей.
}
