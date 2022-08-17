package com.example.roomapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [BeerEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BeersTypeConverter::class)
abstract class BeersDatabase: RoomDatabase() {
    abstract fun beerDao(): BeersDao
}