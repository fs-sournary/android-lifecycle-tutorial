package com.sournary.androidlifecycletutorial.ui.timer

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created in 8/27/19 by Sang
 * Description:
 */
class TimerViewModel : ViewModel() {

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long> = _elapsedTime

    init {
        handleTimer()
    }

    private fun handleTimer() {
        val start = SystemClock.elapsedRealtime()
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - start) / 1000
                _elapsedTime.postValue(newValue)
            }
        }, 1000, 100)
    }
}
