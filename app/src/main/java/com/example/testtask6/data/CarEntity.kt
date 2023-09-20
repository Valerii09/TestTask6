package com.example.testtask6.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val photoUrl: String,
    val year: Int,
    val engineCapacity: Double,
    val dateAdded: Long
)