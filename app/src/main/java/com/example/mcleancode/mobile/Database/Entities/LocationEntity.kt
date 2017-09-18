package com.example.mcleancode.mobile.Database.Entities

data class LocationEntity(
        val title: String,
        val lat: Float,
        val long: Float,
        val radius: Int,
        val entry: String,
        val status: Int
)