package com.zarudna.foodplacesnearby.data.repository

import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.data.database.dao.PlaceDao
import com.zarudna.foodplacesnearby.data.network.PlacesWebservice
import com.zarudna.foodplacesnearby.model.entiry.Place
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepository @Inject constructor(
    private var placeDao: PlaceDao,
    private var placesWebservice: PlacesWebservice
) {

    suspend fun reloadPlaces(latLng: LatLng, limit: Int): List<Place> {
        val places = placesWebservice.loadFoodPlaces(latLng, limit)

        deleteAll()
        save(places)

        return places
    }

    private suspend fun save(places: List<Place>) {
        placeDao.insert(places)
    }

    private suspend fun deleteAll() {
        placeDao.deleteAll()
    }

    fun getSavedPlaces(
        farLeft: LatLng,
        farRight: LatLng,
        nearLeft: LatLng,
        nearRight: LatLng
    ): List<Place> {
        return placeDao.getAll(farLeft, farRight, nearLeft, nearRight)
    }
}