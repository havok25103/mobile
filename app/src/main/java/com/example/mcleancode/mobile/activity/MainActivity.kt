package com.example.mcleancode.mobile.activity

import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.drawable.ScannerDrawable
import com.example.mcleancode.mobile.game.GameWorld
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

class MainActivity : Activity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mGameWorld: GameWorld? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanner = findViewById(R.id.scanner) as ImageView
        scanner.background = ScannerDrawable()

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

    override fun onLocationChanged(location: Location?) {
        // update map
    }

    override fun onConnected(bundle: Bundle?) {
        val location =  LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)

        if(location != null) {
            // Update Map
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.i("onConnectionSuspended", "Location services suspended.")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i("onConnectionFailed", "Location services connection failed with code ${connectionResult.errorCode}")
    }
}
