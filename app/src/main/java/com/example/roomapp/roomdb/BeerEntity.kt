package com.example.roomapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomapp.model.Beers

@Entity(
    tableName = "beers"
)
class BeerEntity(
    val beerEntityModel: Beers
    ) {
    @PrimaryKey(autoGenerate = true)
    var generatedId:Int = 0

}