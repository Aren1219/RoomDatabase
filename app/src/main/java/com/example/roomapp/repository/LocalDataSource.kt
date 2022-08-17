package com.example.roomapp.repository

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.roomapp.api.ApiDetails
import com.example.roomapp.roomdb.BeerEntity
import com.example.roomapp.roomdb.BeersDao
import com.example.roomapp.roomdb.BeersDatabase
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val beersDao: BeersDao
) {
    fun insertBeerIntoDb(beerEntity: BeerEntity) = beersDao.createBeerInsideDb(beerEntity)

    fun readBeerFromDb() = beersDao.readBeersFromDb()
}