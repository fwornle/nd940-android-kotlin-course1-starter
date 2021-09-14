package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import timber.log.Timber

// <layout> tag in XML plus build option "databinding true" (gradle.build) needed to generate
// object ActivityMainBinding -> needed to inflate the activity and make fragment available for nav
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize Timber
        Timber.plant(Timber.DebugTree())

        // needed to inflate the activity layout (thereby making the fragment available for nav)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // solution for <androidx.fragment.app.FragmentContainerView> based code
        // (this also works for <fragment> based code):
        // [inspired from:]
        // https://stackoverflow.com/questions/53902494/navigation-component-cannot-find-navcontroller
        // ... which refers to
        // https://stackoverflow.com/questions/50502269/illegalstateexception-link-does-not-have-a-navcontroller-set
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // configure app bar in navController (for the menu)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // set-up ActionBar / Toolbar
        // ... crashes the app (null pointer exception - setTitle)
        // ... removing this line keeps the app alive, but at the expense of a working toolbar
        // NavigationUI.setupActionBarWithNavController(this, navController)

    }
}
