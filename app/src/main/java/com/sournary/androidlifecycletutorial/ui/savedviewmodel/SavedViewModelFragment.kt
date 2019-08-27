package com.sournary.androidlifecycletutorial.ui.savedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import com.sournary.androidlifecycletutorial.R
import kotlinx.android.synthetic.main.fragment_saved_viewmodel.*

/**
 * Created in 8/27/19 by Sang
 * Description:
 */
class SavedViewModelFragment : Fragment() {

    private val viewModel: SavedStateViewModel by viewModels {
        SavedStateViewModelFactory(activity!!.application, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_saved_viewmodel, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.name.observe(viewLifecycleOwner, Observer {
            nameText.text = it
        })
        applyButton.setOnClickListener {
            viewModel.saveNewName(nameEditText.text.toString())
        }
    }
}
