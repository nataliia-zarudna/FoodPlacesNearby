package com.zarudna.foodplacesnearby.data.network.arcgis

import android.util.Log
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.tasks.geocode.GeocodeParameters
import com.esri.arcgisruntime.tasks.geocode.LocatorTask
import com.google.android.gms.maps.model.LatLng
import com.zarudna.foodplacesnearby.data.network.PlacesWebservice
import com.zarudna.foodplacesnearby.model.entiry.Place
import com.zarudna.foodplacesnearby.ui.activity.MainActivity
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PlacesArcGISWebservice : PlacesWebservice {

    companion object {
        const val GEOCODE_SERVER_URL =
            "https://geocode-api.arcgis.com/arcgis/rest/services/World/GeocodeServer"
        const val CATEGORY_FOOD = "Food"
        const val ATTR_PLACE_NAME = "PlaceName"
        const val ATTR_PLACE_ADDRESS = "Place_addr"
        const val ATTR_PLACE_PHONE = "Phone"
    }

    override suspend fun loadFoodPlaces(latLng: LatLng, limit: Int): List<Place> {
        return suspendCoroutine { continuation ->
            val locatorTask =
                LocatorTask(GEOCODE_SERVER_URL)

            val geocodeParameters = GeocodeParameters().apply {
                categories.add(CATEGORY_FOOD)
                maxResults = limit
                preferredSearchLocation =
                    Point(latLng.longitude, latLng.latitude, SpatialReferences.getWgs84())
                resultAttributeNames.add(ATTR_PLACE_NAME)
                resultAttributeNames.add(ATTR_PLACE_ADDRESS)
                resultAttributeNames.add(ATTR_PLACE_PHONE)
            }

            val geocodeResultsFuture = locatorTask.geocodeAsync("", geocodeParameters)

            geocodeResultsFuture.addDoneListener {
                try {
                    val geocodeResults = geocodeResultsFuture.get()
                    val places = geocodeResults.map {
                        Place(
                            it.label,
                            it.attributes[ATTR_PLACE_ADDRESS].toString(),
                            it.attributes[ATTR_PLACE_PHONE].toString(),
                            it.displayLocation.y,
                            it.displayLocation.x
                        )
                    }

                    continuation.resume(places)

                } catch (e: Exception) {
                    Log.e(MainActivity::class.simpleName, e.message ?: "Failed to load places")
                }
            }
        }
    }
}