package com.sournary.androidlifecycletutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sournary.androidlifecycletutorial.ext.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setSupportActionBar(toolbar)
            setupBottomNav()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setSupportActionBar(toolbar)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navGraphIds = listOf(
            R.navigation.nav_chronometer,
            R.navigation.nav_timer,
            R.navigation.nav_lifecycle,
            R.navigation.nav_saved_viewmodel
        )
        val controller = bottomNav.setupWithNavController(
            navGraphIds, supportFragmentManager, R.id.mainContainer, intent
        )
        controller.observe(this, Observer {
            setupActionBarWithNavController(it)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false
}
