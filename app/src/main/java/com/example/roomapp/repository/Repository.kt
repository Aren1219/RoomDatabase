package com.example.roomapp.repository

class Repository(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remoteDataSource = remoteDataSource
    val localDataSource = localDataSource
}