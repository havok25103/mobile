package com.example.mcleancode.mobile.Database.Seeds

import com.example.mcleancode.mobile.Database.Entities.LocationEntity

class Seed {
    companion object {
        val locations = arrayListOf(
            LocationEntity(
                id = 1,
                title = "Far",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 1
            ),
            LocationEntity(
                id = 2,
                title = "Near",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 2
            ),
            LocationEntity(
                id = 3,
                title = "Here",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 4
            ),
            LocationEntity(
                id = 4,
                title = "Found",
                lat = 28.5383f,
                long = 81.3792f,
                radius = 20,
                entry = "Lorem Ipsum",
                status = 8
            ),
            LocationEntity(
                id = 5,
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