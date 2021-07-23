package com.zarudna.foodplacesnearby.data.database.dao.realm

import com.zarudna.foodplacesnearby.data.database.dao.PlaceDao
import com.zarudna.foodplacesnearby.model.entiry.Place
import io.realm.Realm

class PlaceRealmDao(var realm: Realm) : PlaceDao {

    override suspend fun upsert(places: List<Place>) {
        realm.executeTransactionAsync { transaction ->
            transaction.insertOrUpdate(places)
        }
    }

    override fun getAll(): List<Place> {
        val realmPlaces = realm.where(Place::class.java).findAll()
        return realm.copyFromRealm(realmPlaces)
    }
}