package com.zarudna.foodplacesnearby.injections

import com.zarudna.foodplacesnearby.ui.fragment.ListFragment
import com.zarudna.foodplacesnearby.ui.fragment.MapFragment
import com.zarudna.foodplacesnearby.ui.viewmodel.PlacesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DaoModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(mapFragment: MapFragment)

    fun inject(listFragment: ListFragment)

    fun inject(placesViewModel: PlacesViewModel)

}