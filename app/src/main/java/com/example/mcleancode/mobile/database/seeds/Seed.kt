package com.example.mcleancode.mobile.database.seeds

import com.example.mcleancode.mobile.database.entities.LocationEntity

class Seed {
    companion object {
        val locations = arrayListOf(
                LocationEntity(
                    title = "Foo",
                    lat = 28.5383f,
                    long = 81.3792f,
                    radius = 20,
                    entry = "Lorem Ipsum",
                    status = 0
                ),
                LocationEntity(
                    title = "Bar",
                    lat = 28.5383f,
                    long = 81.3792f,
                    radius = 20,
                    entry = "Lorem Ipsum",
                    status = 0
                ),
                LocationEntity(
                    title = "Baz",
                    lat = 28.5383f,
                    long = 81.3792f,
                    radius = 20,
                    entry = "Lorem Ipsum",
                    status = 0
                )
            )
    }
}