package com.example.roomapp.api

import com.example.roomapp.model.Beers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDetails {
    @GET("beers?page=2&per_page=80")
    suspend fun getBeers(): Response<Beers>
}