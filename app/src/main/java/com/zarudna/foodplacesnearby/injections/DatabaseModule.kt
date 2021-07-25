package com.zarudna.foodplacesnearby.injections;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
class DatabaseModule {

    @Provides
    fun providesRealm(): Realm {
        return Realm.getDefaultInstance();
    }
}
