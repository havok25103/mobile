package com.example.mcleancode.mobile.activity

import android.location.Location
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.ImageView
import com.example.mcleancode.mobile.R
import com.example.mcleancode.mobile.database.helpers.MobileDataSource
import com.example.mcleancode.mobile.drawable.scanner.ScannerDrawable
import com.example.mcleancode.mobile.game.GameWorld
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

class MainActivity : FragmentActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mGameWorld: GameWorld? = null

    private val mScannerDrawable: ScannerDrawable = ScannerDrawable()

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanner = findViewById(R.id.scanner) as ImageView
        scanner.background = mScannerDrawable

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
        if(counter == 0) {
            mScannerDrawable.setFarToNear()
            counter++
        } else if(counter == 1) {
            mScannerDrawable.setNearToHere()
            counter++
        } else if(counter == 2) {
            mScannerDrawable.setHereToNear()
            counter++
        } else if(counter == 3) {
            mScannerDrawable.setNearToFar()
            counter = 0
        }
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
