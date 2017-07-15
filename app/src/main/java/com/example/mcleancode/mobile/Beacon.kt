package com.example.mcleancode.mobile

import android.location.Location

class Beacon(val center: Location, val radius: Float) {
    init {
       // Do beacon things
    }

    fun inRange(currentLoacation: Location): Boolean {
        // Use the distance from the center of the beacon to indicate if you are close enough to get a signal
        return true
    }

    private fun metersFromCenter(currentLoacation: Location): Float {
        // Calculate how close you are to the center of the beacon
        return 0.0f
    }
}