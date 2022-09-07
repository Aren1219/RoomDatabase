package com.example.roomapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BeersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeersToDB(beerEntity: BeerEntity)

    @Query("SELECT * FROM beers")
    suspend fun getBeersFromDB(): BeerEntity

    @Query("SELECT * FROM beers")
    fun readBeersFromDB(): LiveData<BeerEntity>
}