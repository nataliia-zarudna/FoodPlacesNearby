package com.zarudna.foodplacesnearby.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zarudna.foodplacesnearby.databinding.FragmentListBinding
import com.zarudna.foodplacesnearby.model.entity.Place
import com.zarudna.foodplacesnearby.ui.adapter.PlacesAdapter

class ListFragment : Fragment(), PlacesAdapter.OnPlaceClickListener {

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private var placesAdapter: PlacesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        placesAdapter = PlacesAdapter(this)
        binding.places.adapter = placesAdapter

        return binding.root
    }

    override fun onPlaceClick(place: Place) {

    }
}