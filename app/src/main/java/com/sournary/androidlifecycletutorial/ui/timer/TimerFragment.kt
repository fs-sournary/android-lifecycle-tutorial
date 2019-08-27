package com.sournary.androidlifecycletutorial.ui.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sournary.androidlifecycletutorial.R
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * Created in 8/27/19 by Sang
 * Description:
 */
class TimerFragment : Fragment() {

    private val viewModel: TimerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_timer, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.elapsedTime.observe(viewLifecycleOwner, Observer {
            timerText.text = "$it elapsed time"
        })
    }
}
