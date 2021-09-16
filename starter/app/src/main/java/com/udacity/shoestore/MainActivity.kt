package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // set Toolbar (layout element <androidx.appcompat.widget.Toolbar>) as AppBar
        // (... thereby replacing the "native system ActionBar" - see:
        //      https://developer.android.com/training/appbar/setting-up )
        // --> gives the app a basic ActionBar (destination label & overflow menu)
        setSupportActionBar(binding.toolbar)

        // solution for <androidx.fragment.app.FragmentContainerView> based code
        // (this also works for <fragment> based code):
        // [inspired from:]
        // https://stackoverflow.com/questions/53902494/navigation-component-cannot-find-navcontroller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // configure app bar in navController
        // ... adds navigation 'up' icon on all pages which are not "top level"
        // docs: https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
        appBarConfiguration = AppBarConfiguration(setOf(R.id.loginFragment, R.id.shoeListFragment))

        // use AppBar config with (custom) toolbar
        // this set-up does not require overriding of callback "onSupportNavigateUp"
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

}
