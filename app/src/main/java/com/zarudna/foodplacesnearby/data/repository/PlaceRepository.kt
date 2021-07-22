package com.zarudna.foodplacesnearby.data.repository

import com.zarudna.foodplacesnearby.data.database.dao.PlaceDao
import com.zarudna.foodplacesnearby.model.entity.Place
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRepository(var placeDao: PlaceDao) {

    suspend fun save(places: List<Place>) {
        withContext(Dispatchers.IO) {
            placeDao.upsert(places)
        }
    }

    suspend fun getAll(): List<Place> {
        val realmInst = Realm.getDefaultInstance()
        val realmPlaces = realmInst.where(Place::class.java).findAll()
        return realmInst.copyFromRealm(realmPlaces)
    }
}