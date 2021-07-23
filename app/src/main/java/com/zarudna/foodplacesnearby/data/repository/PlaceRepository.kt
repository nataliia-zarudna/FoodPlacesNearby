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

    suspend fun loadPlaces(latLng: LatLng, limit: Int): List<Place> {
        val places = placesWebservice.loadFoodPlaces(latLng, limit);
        save(places)

        return places
    }

    suspend fun save(places: List<Place>) {
        return placeDao.upsert(places)
    }

    fun getAll(): List<Place> {
        return placeDao.getAll()
    }
}