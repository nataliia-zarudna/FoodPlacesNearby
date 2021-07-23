package com.zarudna.foodplacesnearby.data.database.dao

import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.model.entiry.Place

interface PlaceDao {

    suspend fun insert(places: List<Place>)

    suspend fun deleteAll()

    fun getAll(farLeft: LatLng,
               farRight: LatLng,
               nearLeft: LatLng,
               nearRight: LatLng
    ): List<Place>
}