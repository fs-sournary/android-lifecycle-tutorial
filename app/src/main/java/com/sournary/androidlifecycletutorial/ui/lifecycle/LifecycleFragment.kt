package com.sournary.androidlifecycletutorial.ui.lifecycle

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sournary.androidlifecycletutorial.R
import com.sournary.androidlifecycletutorial.util.BoundLocationCallback
import kotlinx.android.synthetic.main.fragment_lifecycle.*

/**
 * Created in 8/26/19 by Sang
 * Description:
 */
class LifecycleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_lifecycle, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val condition = ContextCompat.checkSelfPermission(
            context!!, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            context!!, Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        if (condition) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
            requestPermissions(permissions, 1)
        } else {
            bindLocationListener()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            bindLocationListener()
        }
    }

    @SuppressLint("RequiresFeature")
    private fun bindLocationListener() {
        BoundLocationCallback(LifecycleLocationListener(), this, context!!.applicationContext)
    }

    private inner class LifecycleLocationListener : LocationListener {

        @SuppressLint("SetTextI18n")
        override fun onLocationChanged(location: Location) {
            latText.text = "Lat: ${location.latitude}"
            longText.text = "Long: ${location.longitude}"
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    }
}
