package com.example.mcleancode.mobile.database.seeds

import com.example.mcleancode.mobile.database.criteria.LocationCriteria

class Seed {
    companion object {
        val locations = arrayListOf(
                LocationCriteria(
                    title = "Foo",
                    lat = 28.5383f,
                    long = 81.3792f,
                    radius = 20,
                    entry = "Lorem Ipsum",
                    status = 0
                ),
                LocationCriteria(
                    title = "Bar",
                    lat = 28.5383f,
                    long = 81.3792f,
                    radius = 20,
                    entry = "Lorem Ipsum",
                    status = 0
                ),
                LocationCriteria(
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