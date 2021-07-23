package com.zarudna.foodplacesnearby.injections

import com.zarudna.foodplacesnearby.data.network.arcgis.PlacesArcGISWebservice
import com.zarudna.foodplacesnearby.data.network.PlacesWebservice
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun providePlacesWebservice(): PlacesWebservice {
        return PlacesArcGISWebservice()
    }
}