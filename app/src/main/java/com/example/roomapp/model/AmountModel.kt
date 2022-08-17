package com.example.roomapp.model


import com.google.gson.annotations.SerializedName

data class AmountModel(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)