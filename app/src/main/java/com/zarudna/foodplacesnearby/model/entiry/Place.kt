package com.zarudna.foodplacesnearby.model.entiry

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject

open class Place(
    var title: String? = "",
    var address: String? = "",
    var phone: String? = "",
    var lat: Double? = null,
    var lng: Double? = null
) : RealmObject(), Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeValue(lat)
        parcel.writeValue(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }
    }
}