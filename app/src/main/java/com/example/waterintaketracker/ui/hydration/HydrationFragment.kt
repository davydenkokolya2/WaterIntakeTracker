package com.example.waterintaketracker.ui.hydration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.waterintaketracker.databinding.FragmentHydrationBinding
import com.example.waterintaketracker.ui.MainNavViewModel
import com.example.waterintaketracker.util.MainNav
import kotlinx.coroutines.launch

class HydrationFragment : Fragment() {

    private lateinit var binding: FragmentHydrationBinding
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    private val hydrationViewModel: HydrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHydrationBinding.inflate(inflater, container, false)

        binding.btnHomeHydration.setOnClickListener {
            mainNavViewModel.loadState(MainNav.HOME)
        }
        binding.progressBar.max = 2600

        lifecycleScope.launch {
            hydrationViewModel.stateHydration.collect {
                binding.progressBar.progress = it[0] + it[1] + it[2] + it[3]
                binding.tvCountBottle.text = "${it[0]} ml"
                binding.tvCountCoffee.text = "${it[2]} ml"
                binding.tvCountTea.text = "${it[3]} ml"
                binding.tvCountTeaMilk.text = "${it[1]} ml"
                binding.tvCountCommonWater.text = "${it[0] + it[1] + it[2] + it[3]} ml"
                val percentage: Int = ((it[0] + it[1] + it[2] + it[3]) / 2600F * 100).toInt()
                binding.tvPercent.text = "${percentage}%"
            }
        }
        return binding.root
    }
}