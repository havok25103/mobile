package com.example.mcleancode.mobile.Enums

enum class LocationStatusEnum(val status: Int) {
    Far(0),
    Near(1),
    Here(2),
    Found(3),
    NotRead(4),
    Read(5)
}