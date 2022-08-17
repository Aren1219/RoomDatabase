package com.example.roomapp.model


import com.google.gson.annotations.SerializedName

data class FermentationModel(
    @SerializedName("temp")
    val temp: TempModel
)