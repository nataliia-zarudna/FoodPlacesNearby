package com.zarudna.foodplacesnearby.data.database.dao

import com.zarudna.foodplacesnearby.model.entity.Place

interface PlaceDao {

    fun upsert(places: List<Place>)

    fun getAll(): List<Place>
}