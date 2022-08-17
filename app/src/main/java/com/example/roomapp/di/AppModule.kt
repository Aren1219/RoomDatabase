package com.example.roomapp.di

import android.content.Context
import androidx.room.Room
import com.example.roomapp.api.ApiDetails
import com.example.roomapp.api.ApiReferences.BASE_URL
import com.example.roomapp.repository.LocalDataSource
import com.example.roomapp.repository.RemoteDataSource
import com.example.roomapp.repository.Repository
import com.example.roomapp.roomdb.BeersDao
import com.example.roomapp.roomdb.BeersDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiDetails = retrofit.create(ApiDetails::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiDetails: ApiDetails):RemoteDataSource = RemoteDataSource(apiDetails)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource): Repository=
        Repository(remoteDataSource,localDataSource)

    @Provides
    @Singleton
    fun provideRoom(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BeersDatabase::class.java,
        "BeerDatabase"
    ).build()

    @Provides
    @Singleton
    fun provideBeersDao(database: BeersDatabase) = database.beerDao()
}