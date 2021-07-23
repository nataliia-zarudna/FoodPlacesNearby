package com.zarudna.foodplacesnearby

import android.app.Application
import android.content.pm.PackageManager
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.zarudna.foodplacesnearby.injections.AppComponent
import com.zarudna.foodplacesnearby.injections.DaggerAppComponent
import io.realm.Realm

class MyApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()

        val metaData =
            packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData
        ArcGISRuntimeEnvironment.setApiKey(metaData.getString("arc_gis.API_KEY"))

        Realm.init(this)
    }
}