package com.zarudna.foodplacesnearby.data.database.dao

import com.zarudna.foodplacesnearby.model.entiry.Place

interface PlaceDao {

    suspend fun upsert(places: List<Place>)

    fun getAll(): List<Place>
}