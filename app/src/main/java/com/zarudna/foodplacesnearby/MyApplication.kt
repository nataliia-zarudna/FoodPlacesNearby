package com.zarudna.foodplacesnearby

import android.app.Application
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.zarudna.foodplacesnearby.injections.DaggerAppComponent
import io.realm.Realm

class MyApplication : Application() {

    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()

        ArcGISRuntimeEnvironment.setApiKey(getString(R.string.arc_gis_key));
        Realm.init(this)
    }
}