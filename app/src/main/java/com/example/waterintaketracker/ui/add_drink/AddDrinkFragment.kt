package com.example.waterintaketracker.ui.add_drink

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.waterintaketracker.R
import com.example.waterintaketracker.databinding.FragmentAddDrinkBinding
import com.example.waterintaketracker.ui.MainNavViewModel
import com.example.waterintaketracker.ui.hydration.HydrationViewModel
import com.example.waterintaketracker.util.Drink
import com.example.waterintaketracker.util.MainNav
import kotlinx.coroutines.launch

class AddDrinkFragment : Fragment() {

    private lateinit var binding: FragmentAddDrinkBinding
    private val addDrinkViewModel: AddDrinkViewModel by activityViewModels()
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    private val hydrationViewModel: HydrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDrinkBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            addDrinkViewModel.stateAddDrink.collect {
                binding.btnCoffee.setImageResource(R.drawable.icon_card_coffee_false)
                binding.btnCoke.setImageResource(R.drawable.icon_card_coke_false)
                binding.btnJuice.setImageResource(R.drawable.icon_card_juice_false)
                binding.btnWater.setImageResource(R.drawable.icon_card_water_false)
                binding.tvCoffeeAdd.setTextColor(Color.GRAY)
                binding.tvCokeAdd.setTextColor(Color.GRAY)
                binding.tvJuiceAdd.setTextColor(Color.GRAY)
                binding.tvWaterAdd.setTextColor(Color.GRAY)

                when (it) {
                    Drink.COFFEE -> {
                        binding.btnCoffee.setImageResource(R.drawable.icon_card_coffee_true)
                        binding.tvCoffeeAdd.setTextColor(Color.WHITE)
                    }

                    Drink.COKE -> {
                        binding.btnCoke.setImageResource(R.drawable.icon_card_coke_true)
                        binding.tvCokeAdd.setTextColor(Color.WHITE)
                    }

                    Drink.JUICE -> {
                        binding.btnJuice.setImageResource(R.drawable.icon_card_juice_true)
                        binding.tvJuiceAdd.setTextColor(Color.WHITE)
                    }

                    Drink.WATER -> {
                        binding.btnWater.setImageResource(R.drawable.icon_card_water_true)
                        binding.tvWaterAdd.setTextColor(Color.WHITE)
                    }

                    else -> {

                    }
                }
            }
        }

        binding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvCurrentML.text = "${progress * 10} ml"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        binding.btnJuice.setOnClickListener {
            addDrinkViewModel.loadState(Drink.JUICE)
        }
        binding.btnWater.setOnClickListener {
            addDrinkViewModel.loadState(Drink.WATER)
        }
        binding.btnCoke.setOnClickListener {
            addDrinkViewModel.loadState(Drink.COKE)
        }
        binding.btnCoffee.setOnClickListener {
            addDrinkViewModel.loadState(Drink.COFFEE)
        }
        binding.btnAdd.setOnClickListener {
            when (addDrinkViewModel.stateAddDrink.value) {
                Drink.COFFEE -> hydrationViewModel.loadState(2, binding.seekBar2.progress * 10)
                Drink.JUICE -> hydrationViewModel.loadState(3, binding.seekBar2.progress * 10)
                Drink.COKE -> hydrationViewModel.loadState(1, binding.seekBar2.progress * 10)
                Drink.WATER -> hydrationViewModel.loadState(0, binding.seekBar2.progress * 10)
                else -> {}
            }
            mainNavViewModel.loadState(MainNav.HOME)
        }

        return binding.root
    }
}