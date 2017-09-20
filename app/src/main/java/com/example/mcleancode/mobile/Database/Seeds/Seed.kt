package com.example.mcleancode.mobile.Database.Seeds

import com.example.mcleancode.mobile.Database.Entities.LocationEntity

class Seed {
    companion object {
        val locations = arrayListOf(
            LocationEntity(
                title = "Far",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 1
            ),
            LocationEntity(
                title = "Near",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 2
            ),
            LocationEntity(
                title = "Here",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 4
            ),
            LocationEntity(
                title = "Found",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 8
            ),
            LocationEntity(
                title = "Read",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 16
            )
        )
    }
}