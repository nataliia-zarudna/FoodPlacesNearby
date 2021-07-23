package com.zarudna.foodplacesnearby.ui.fragment

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zarudna.foodplacesnearby.R
import com.zarudna.foodplacesnearby.databinding.FragmentMapBinding
import com.zarudna.foodplacesnearby.model.entiry.Place
import com.zarudna.foodplacesnearby.ui.viewmodel.PlacesViewModel


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    companion object {
        private const val INIT_ZOOM_LEVEL = 14.0
        private val DEFAULT_INIT_LOCATION = LatLng(40.7484445, -73.9878531)
    }

    private val binding by lazy {
        FragmentMapBinding.inflate(layoutInflater)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap

    private lateinit var viewModel: PlacesViewModel

    private val requestLocationLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[ACCESS_FINE_LOCATION] == true
                && permissions[ACCESS_COARSE_LOCATION] == true
            ) {
                loadLocation()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(it).get(PlacesViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMap()
    }

    private fun setupMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        updateLocation()

        viewModel.placesLiveData.observe(viewLifecycleOwner) { places ->
            displaySearchResult(places)
        }
    }

    private fun displaySearchResult(places: List<Place>) {
        for (place in places) {
            if (place.lat != null && place.lng != null) {
                map.addMarker(
                    MarkerOptions()
                        .position(LatLng(place.lat!!, place.lng!!))
                )
            }
        }
    }

    override fun onCameraIdle() {
        viewModel.loadPlaces(
            map.cameraPosition.target,
            map.projection.visibleRegion.farLeft,
            map.projection.visibleRegion.farRight,
            map.projection.visibleRegion.nearLeft,
            map.projection.visibleRegion.nearRight
        )
    }

    private fun updateLocation() {
        activity?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)

            if (ActivityCompat.checkSelfPermission(
                    it,
                    ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    it,
                    ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                loadLocation()
            } else {

                requestLocationLauncher.launch(
                    arrayOf(
                        ACCESS_FINE_LOCATION,
                        ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun loadLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location == null) {
                    Toast.makeText(activity, R.string.error_location_not_found, Toast.LENGTH_SHORT)
                        .show()
                }

                val latLng = if (location != null) LatLng(
                    location.latitude,
                    location.longitude
                ) else DEFAULT_INIT_LOCATION

                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        latLng,
                        INIT_ZOOM_LEVEL.toFloat()
                    )
                )

                map.setOnCameraIdleListener(this)
            }
    }
}