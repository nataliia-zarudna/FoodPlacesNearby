package com.zarudna.foodplacesnearby.model.entiry

import io.realm.RealmObject

open class Place(
    var title: String? = "",
    var lat: Double? = null,
    var lng: Double? = null
) : RealmObject()