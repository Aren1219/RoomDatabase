package com.example.roomapp.repository

import com.example.roomapp.api.ApiDetails
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val apiDetails: ApiDetails) {

    suspend fun getBeers() = apiDetails.getBeers()

}