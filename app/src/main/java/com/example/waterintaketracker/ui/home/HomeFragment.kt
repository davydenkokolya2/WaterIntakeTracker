package com.example.waterintaketracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.waterintaketracker.databinding.FragmentHomeBinding
import com.example.waterintaketracker.ui.MainNavViewModel
import com.example.waterintaketracker.ui.hydration.HydrationViewModel
import com.example.waterintaketracker.util.MainNav
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    private val hydrationViewModel: HydrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnAddWater.setOnClickListener {
            mainNavViewModel.loadState(MainNav.ADD)
        }
        binding.btnDrop.setOnClickListener {
            mainNavViewModel.loadState(MainNav.HYDRATION)
        }

        binding.btnBottleHome.setOnClickListener {
            hydrationViewModel.loadState(0, 600)
        }
        binding.btnCoffeeHome.setOnClickListener {
            hydrationViewModel.loadState(2, 290)
        }
        binding.btnTeaHome.setOnClickListener {
            hydrationViewModel.loadState(3, 250)
        }
        binding.btnMilkTeaHome.setOnClickListener {
            hydrationViewModel.loadState(1, 350)
        }
        lifecycleScope.launch {
            hydrationViewModel.stateHydration.collect {
                binding.tvCurrentDrink.text = "${it[0] + it[1] + it[2] + it[3]}"
                val percentage: Int = ((it[0] + it[1] + it[2] + it[3]) / 2600F * 100).toInt()
                binding.tvPercentageHome.text = "${percentage}%"
                binding.progressBar2.progress = it[0] + it[1] + it[2] + it[3]
            }
        }
        return binding.root
    }
}