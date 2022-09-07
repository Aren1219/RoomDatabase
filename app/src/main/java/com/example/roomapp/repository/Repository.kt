package com.example.roomdatabase.repo

import androidx.lifecycle.LiveData
import com.example.roomapp.model.Beers
import com.example.roomapp.roomdb.BeerEntity
import retrofit2.Response

interface Repository {

    suspend fun getBeersFromApi(): Response<Beers>

    suspend fun insertBeers(beerEntity: BeerEntity)

    suspend fun getBeersFromDb(): BeerEntity

    fun readBeersFromDb(): LiveData<BeerEntity>
}