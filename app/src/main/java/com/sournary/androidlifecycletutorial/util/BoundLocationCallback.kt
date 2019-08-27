package com.sournary.androidlifecycletutorial.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import androidx.annotation.RequiresFeature
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created in 8/26/19 by Sang
 * Description:
 */
@RequiresFeature(
    name = "Permission", enforcement = "ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION"
)
class BoundLocationCallback(
    private val listener: LocationListener, lifecycleOwner: LifecycleOwner, context: Context
) : LifecycleObserver {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun addLocationListener() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
        val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        listener.onLocationChanged(lastLocation)
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun removeLocationListener() {
        locationManager.removeUpdates(listener)
    }
}
