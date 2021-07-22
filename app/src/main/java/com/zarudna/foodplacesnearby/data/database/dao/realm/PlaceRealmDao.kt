package com.zarudna.foodplacesnearby.data.database.dao.realm

import com.zarudna.foodplacesnearby.data.database.dao.PlaceDao
import com.zarudna.foodplacesnearby.model.entity.Place
import io.realm.Realm

class PlaceRealmDao : PlaceDao {

    override fun upsert(places: List<Place>) {
        val realmInst = Realm.getDefaultInstance()
        realmInst.executeTransaction { realm ->
            realm.insertOrUpdate(places)
        }
    }

    override fun getAll(): List<Place> {
        val realmInst = Realm.getDefaultInstance()
        val realmPlaces = realmInst.where(Place::class.java).findAll()
        return realmInst.copyFromRealm(realmPlaces)
    }
}