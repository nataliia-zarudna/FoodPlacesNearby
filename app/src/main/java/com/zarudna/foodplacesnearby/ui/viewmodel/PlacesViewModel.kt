package com.zarudna.foodplacesnearby.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.data.repository.PlaceRepository
import com.zarudna.foodplacesnearby.model.entiry.Place
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlacesViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : ViewModel() {

    companion object {
        private const val MAX_SEARCH_LOCATIONS = 20
    }

    val placesLiveData = MutableLiveData<List<Place>>()

    public fun loadPlaces(
        target: LatLng,
        farLeft: LatLng,
        farRight: LatLng,
        nearLeft: LatLng,
        nearRight: LatLng
    ) {

        viewModelScope.launch {
            val places = placeRepository.loadPlaces(target, MAX_SEARCH_LOCATIONS)
            placesLiveData.value = places
        }
    }

}