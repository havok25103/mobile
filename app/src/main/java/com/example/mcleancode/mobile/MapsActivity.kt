package com.example.mcleancode.mobile

import android.app.PendingIntent
import android.content.Intent
import android.location.Location
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.location.Geofence



class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private val mMapUpdater: MapUpdater = MapUpdater()
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mGeofencingClient: GeofencingClient? = null
    private var mGeofencePendingIntent: PendingIntent? = null
    private var mLocationRequest: LocationRequest? = null
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        mGeofencingClient = LocationServices.getGeofencingClient(this)

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000)

        mapFragment().getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mGoogleApiClient!!.connect()

        val latitude = 51.5033640
        val longitude = -0.1276250
        val radiusInMeters = 20.0f
        val expirationInMilliseconds = DateUtils.DAY_IN_MILLIS

        val geofence = Geofence.Builder()
                .setRequestId("Foo")
                .setCircularRegion(latitude, longitude, radiusInMeters)
                .setExpirationDuration(expirationInMilliseconds)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()

        val geofenceList: MutableList<Geofence> = arrayListOf()
        geofenceList.add(geofence)

        val builder = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofences(geofenceList)
            .build()

        var pendingIntent: PendingIntent?
        if(mGeofencePendingIntent != null) {
            pendingIntent = mGeofencePendingIntent
        } else {
            val intent = Intent(this, GeofenceTransitionsIntentService::class.java)
            pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        mGeofencingClient!!.addGeofences(builder, pendingIntent)
                .addOnSuccessListener { Log.i("Geofence I On Success", "Trigger") }
                .addOnFailureListener { Log.i("Geofence I On Failure", "Trigger") }

    }

    override fun onPause() {
        super.onPause()

        if(mGoogleApiClient!!.isConnected) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
            mGoogleApiClient!!.disconnect()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onLocationChanged(location: Location?) {
        mMapUpdater.update(mMap, location)
    }

    override fun onConnected(bundle: Bundle?) {
        val location =  LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)

        if(location != null) {
            mMapUpdater.update(mMap, location)
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.i("onConnectionSuspended", "Location services suspended.")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i("onConnectionFailed", "Location services connection failed with code ${connectionResult.errorCode}")
    }

    private fun mapFragment(): SupportMapFragment {
        return supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }
}
