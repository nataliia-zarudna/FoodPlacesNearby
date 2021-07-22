package com.zarudna.foodplacesnearby.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zarudna.foodplacesnearby.databinding.FragmentPlaceDetailsBinding

class PlaceDetailsFragment() : Fragment() {

    private val binding by lazy {
        FragmentPlaceDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.title.text = arguments?.getString("place")
    }
}