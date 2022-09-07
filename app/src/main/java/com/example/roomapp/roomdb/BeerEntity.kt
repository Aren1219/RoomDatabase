package com.example.roomapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomapp.model.Beers

@Entity(
    tableName = "beers"
)
class BeerEntity(
    val beersList: Beers
    ) {
    @PrimaryKey(autoGenerate = false)
    var generatedId:Int = 0
}