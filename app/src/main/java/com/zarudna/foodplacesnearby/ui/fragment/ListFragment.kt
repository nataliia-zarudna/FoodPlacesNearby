package com.zarudna.foodplacesnearby.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.zarudna.foodplacesnearby.databinding.FragmentListBinding
import com.zarudna.foodplacesnearby.model.entiry.Place
import com.zarudna.foodplacesnearby.ui.adapter.PlacesAdapter
import com.zarudna.foodplacesnearby.ui.viewmodel.PlacesViewModel

class ListFragment : Fragment(), PlacesAdapter.OnPlaceClickListener {

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: PlacesViewModel

    private var placesAdapter: PlacesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(it).get(PlacesViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        placesAdapter = PlacesAdapter(this)
        binding.places.adapter = placesAdapter

        viewModel.placesLiveData.observe(viewLifecycleOwner) { places ->
            placesAdapter?.places = places
        }
    }

    override fun onPlaceClick(place: Place) {
        val action = PlacesFragmentDirections.actionPlacesToPlaceDetails(place)
        view?.findNavController()?.navigate(action)
    }
}