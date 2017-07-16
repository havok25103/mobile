package com.example.mcleancode.mobile

import android.text.format.DateUtils
import com.google.android.gms.location.Geofence

class Beacon(val centerLat: Double, val centerLong: Double, val radiusInMeters: Float, val id: String) {

    private val expirationInMilliseconds = DateUtils.DAY_IN_MILLIS

    val geofence: Geofence = Geofence.Builder()
            .setRequestId(id)
            .setCircularRegion(centerLat, centerLong, radiusInMeters)
            .setExpirationDuration(expirationInMilliseconds)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()
}