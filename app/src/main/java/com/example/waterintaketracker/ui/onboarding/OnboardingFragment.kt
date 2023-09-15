package com.example.waterintaketracker.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.waterintaketracker.R
import com.example.waterintaketracker.databinding.FragmentOnboardingBinding
import com.example.waterintaketracker.ui.MainNavViewModel
import com.example.waterintaketracker.util.MainNav
import kotlinx.coroutines.launch

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            onboardingViewModel.stateOnboarding.collect {
                when(it) {
                    0 -> {
                        binding.ivOnboarding.setImageResource(R.drawable.icon_image_onboarding_1)
                        binding.tvOnboarding.setText(R.string.next)
                        binding.tvTextOnboarding.setText(R.string.onboarding_1)
                    }
                    1 -> {
                        binding.ivOnboarding.setImageResource(R.drawable.icon_image_onboarding_2)
                        binding.tvOnboarding.setText(R.string.start)
                        binding.tvTextOnboarding.setText(R.string.onboarding_2)
                    }
                }
            }
        }

        binding.imageView2.setOnClickListener {
            if (onboardingViewModel.stateOnboarding.value == 0)
                onboardingViewModel.loadState(1)
            else
                mainNavViewModel.loadState(MainNav.HOME)
        }

        return binding.root
    }
}