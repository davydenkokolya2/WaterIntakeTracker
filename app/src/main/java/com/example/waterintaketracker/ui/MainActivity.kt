package com.example.waterintaketracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.waterintaketracker.R
import com.example.waterintaketracker.ui.add_drink.AddDrinkFragment
import com.example.waterintaketracker.ui.home.HomeFragment
import com.example.waterintaketracker.ui.hydration.HydrationFragment
import com.example.waterintaketracker.ui.onboarding.OnboardingFragment
import com.example.waterintaketracker.util.MainNav
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainNavViewModel: MainNavViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            mainNavViewModel.stateMainNav.collect {
                when(it) {
                    MainNav.ADD -> replaceFragment(AddDrinkFragment())
                    MainNav.HOME -> replaceFragment(HomeFragment())
                    MainNav.ONBOARDING -> replaceFragment(OnboardingFragment())
                    MainNav.HYDRATION -> replaceFragment(HydrationFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}