package com.example.mcleancode.mobile.Enums

enum class LocationStatusEnum(val status: Int) {
    Far(1),
    Near(2),
    Here(4),
    Found(8),
    Read(16)
}