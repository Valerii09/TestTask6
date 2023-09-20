package com.example.testtask6.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class], version = 1)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
    companion object {
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                try {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarDatabase::class.java,
                        "car_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                } catch (e: Exception) {
                    // Выводим информацию об ошибке в Logcat
                    Log.e("CarDatabase", "Error creating database", e)
                    throw e
                }
            }
        }
    }

}
