package com.sournary.androidlifecycletutorial.ui.chronometer

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sournary.androidlifecycletutorial.R
import kotlinx.android.synthetic.main.fragment_chronometer.*

/**
 * Created in 8/27/19 by Sang
 * Description:
 */
class ChronometerFragment : Fragment() {

    private val viewModel: ChronometerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chronometer, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startButton.setOnClickListener {
            if (viewModel.startTime == null) {
                val start = SystemClock.elapsedRealtime()
                viewModel.startTime = start
                chronometer.base = start
                chronometer.start()
            }
        }
        stopButton.setOnClickListener {
            if (viewModel.startTime != null) {
                viewModel.startTime = null
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.stop()
            }
        }
        viewModel.startTime?.let {
            chronometer.base = it
            chronometer.start()
        }
    }
}
