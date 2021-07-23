package com.zarudna.foodplacesnearby.injections;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DatabaseModule {

    @Provides
    Realm providesRealm() {
        return Realm.getDefaultInstance();
    }
}
