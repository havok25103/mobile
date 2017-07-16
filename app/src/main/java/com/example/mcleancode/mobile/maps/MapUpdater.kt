package com.example.mcleancode.mobile.maps

import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapUpdater {
    private val zoomLevel = 19.00f

    fun update(map: GoogleMap?, location: Location?) {
        if(location != null && map != null) {
            val currentLatitude = location.latitude
            val currentLongitude = location.longitude
            val currentLocation = LatLng(currentLatitude, currentLongitude)

            map.addMarker(MarkerOptions().position(currentLocation).title("Current Location"))
            map.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))
            map.moveCamera(CameraUpdateFactory.zoomTo(zoomLevel))
        }
    }
}