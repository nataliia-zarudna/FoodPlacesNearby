package com.zarudna.foodplacesnearby.injections

import com.zarudna.foodplacesnearby.data.database.dao.PlaceDao
import com.zarudna.foodplacesnearby.data.database.dao.realm.PlaceRealmDao
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class DaoModule {

    @Provides
    fun providePlaceDao(realm: Realm): PlaceDao {
        return PlaceRealmDao(realm)
    }
}