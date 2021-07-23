package com.zarudna.foodplacesnearby.data.network

import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.model.entiry.Place

interface PlacesWebservice {

    suspend fun loadFoodPlaces(latLng: LatLng, limit: Int):List<Place>
}