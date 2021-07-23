package com.zarudna.foodplacesnearby.data.database.dao.realm

import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.data.database.dao.PlaceDao
import com.zarudna.foodplacesnearby.model.entiry.Place
import io.realm.Realm

class PlaceRealmDao(var realm: Realm) : PlaceDao {

    override suspend fun insert(places: List<Place>) {
        realm.executeTransactionAsync { transaction ->
            transaction.insert(places)
        }
    }

    override suspend fun deleteAll() {
        realm.executeTransactionAsync { transaction ->
            transaction.deleteAll()
        }
    }

    override fun getAll(
        farLeft: LatLng,
        farRight: LatLng,
        nearLeft: LatLng,
        nearRight: LatLng
    ): List<Place> {
        val realmPlaces = realm.where(Place::class.java)
            .lessThanOrEqualTo("lat", farLeft.latitude)
            .greaterThanOrEqualTo("lat", nearLeft.latitude)
            .greaterThanOrEqualTo("lng", farLeft.longitude)
            .lessThanOrEqualTo("lng", farRight.longitude)
            .findAll()
        return realm.copyFromRealm(realmPlaces)
    }
}