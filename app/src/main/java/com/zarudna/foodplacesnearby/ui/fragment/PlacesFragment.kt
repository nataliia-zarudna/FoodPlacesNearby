package com.zarudna.foodplacesnearby.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zarudna.foodplacesnearby.ui.adapter.PlaceTabAdapter
import com.zarudna.foodplacesnearby.R
import com.zarudna.foodplacesnearby.databinding.FragmentPlacesBinding
import java.lang.IllegalArgumentException

class PlacesFragment : Fragment() {

    private val binding by lazy {
        FragmentPlacesBinding.inflate(layoutInflater)
    }

    private lateinit var placeTabAdapter: PlaceTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        placeTabAdapter = PlaceTabAdapter(this)
        binding.pager.adapter = placeTabAdapter
        binding.pager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.setText(when (position) {
                0 -> R.string.tab_map
                1 -> R.string.tab_list
                else -> throw IllegalArgumentException("Wrong place fragment position $position")
            })
        }.attach()
    }

}