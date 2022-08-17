package com.example.roomapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

@Dao
interface BeersDao {

    @androidx.room.Query("SELECT * FROM beers ORDER BY generatedID")
    fun readBeersFromDb(): Flow<List<BeerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createBeerInsideDb(beerEntity: BeerEntity): Long

    @Delete
    suspend fun deleteBeer(beerEntity: BeerEntity)
}