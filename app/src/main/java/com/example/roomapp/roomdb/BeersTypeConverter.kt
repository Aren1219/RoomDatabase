package com.example.roomapp.roomdb

import androidx.room.TypeConverter
import com.example.roomapp.model.Beers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BeersTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun beersToString(beers: Beers): String = gson.toJson(beers)

    @TypeConverter
    fun stringToBeers(data: String): Beers {
        val listType = object : TypeToken<Beers>() {}.type
        return gson.fromJson(data, listType)
    }
}