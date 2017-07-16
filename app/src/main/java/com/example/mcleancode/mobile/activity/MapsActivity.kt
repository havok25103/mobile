package com.example.mcleancode.mobile.activity

import android.location.Location
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.util.Log
import com.example.mcleancode.mobile.game.GameWorld
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.maps.MapUpdater
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private val mMapUpdater: MapUpdater = MapUpdater()
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mGameWorld: GameWorld? = null
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000)

        mGameWorld = GameWorld(this)

        mapFragment().getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mGoogleApiClient!!.connect()
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
