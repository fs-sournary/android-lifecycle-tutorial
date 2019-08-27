package com.sournary.androidlifecycletutorial.ui.savedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Created in 8/27/19 by Sang
 * Description:
 */
class SavedStateViewModel(private val mState: SavedStateHandle) : ViewModel() {

    private val _name: MutableLiveData<String> = mState.getLiveData(NAME_KEY)
    val name: LiveData<String> = _name

    fun saveNewName(newName: String) {
        mState.set(NAME_KEY, newName)
    }

    companion object {

        private const val NAME_KEY = "name"
    }
}