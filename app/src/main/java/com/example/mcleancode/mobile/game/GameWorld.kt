package com.example.mcleancode.mobile.game

import android.content.Context
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices

class GameWorld(context: Context) {

    private val beacons: Array<Beacon> = arrayOf(
            Beacon(
                    id = "UniKey",
                    centerLong = -81.3812620,
                    centerLat = 28.5449380,
                    radiusInMeters = 20.0f
            )
    )

    private val mGeofencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)
    private val geofenceList: MutableList<Geofence> = arrayListOf()
    private val mGeofenceManager: GeofenceManager = GeofenceManager(context, mGeofencingClient)

    init {
        beacons.forEach { beacon -> geofenceList.add(beacon.geofence) }
        mGeofenceManager.addGeofencesToClient(geofenceList)
    }
}