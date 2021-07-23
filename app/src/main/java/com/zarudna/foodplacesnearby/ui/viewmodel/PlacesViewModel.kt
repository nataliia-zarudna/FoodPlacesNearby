package com.zarudna.foodplacesnearby.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.MyApplication
import com.zarudna.foodplacesnearby.data.repository.PlaceRepository
import com.zarudna.foodplacesnearby.model.entiry.Place
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlacesViewModel (
     application: Application
) : AndroidViewModel(application) {

    companion object {
        private const val MAX_SEARCH_LOCATIONS = 20
    }

    @Inject
    lateinit var placeRepository: PlaceRepository

    val placesLiveData = MutableLiveData<List<Place>>()

    init {
        (application as MyApplication).appComponent.inject(this)
    }

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