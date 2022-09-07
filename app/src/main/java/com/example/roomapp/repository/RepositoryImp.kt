package com.example.roomdatabase.repo

import androidx.lifecycle.LiveData
import com.example.roomapp.api.ApiDetails
import com.example.roomapp.model.Beers
import com.example.roomapp.roomdb.BeerEntity
import com.example.roomapp.roomdb.BeersDao
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    val beersApi: ApiDetails,
    val beersDao: BeersDao
): Repository {
    override suspend fun getBeersFromApi(): Response<Beers> = beersApi.getBeers()

    override suspend fun insertBeers(beerEntity: BeerEntity) {
        beersDao.insertBeersToDB(beerEntity)
    }

    override suspend fun getBeersFromDb(): BeerEntity = beersDao.getBeersFromDB()

    override fun readBeersFromDb(): LiveData<BeerEntity> = beersDao.readBeersFromDB()
}