package com.example.testtask6.data

import kotlinx.coroutines.flow.Flow

class CarRepository(private val carDao: CarDao) {
    fun searchCars(searchQuery: String): Flow<List<CarEntity>> {
        return carDao.searchCars(searchQuery)
    }

    suspend fun insertCar(car: CarEntity) {
        carDao.insertCar(car)
    }

    // Другие методы для работы с данными автомобилей.
}