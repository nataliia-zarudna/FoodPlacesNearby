package com.zarudna.foodplacesnearby.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.zarudna.foodplacesnearby.R
import com.zarudna.foodplacesnearby.databinding.FragmentPlaceDetailsBinding
import com.zarudna.foodplacesnearby.model.entiry.Place

class PlaceDetailsFragment() : DialogFragment() {

    private val binding by lazy {
        FragmentPlaceDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.title_details)
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                dialog.dismiss()
            }
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val place: Place? = arguments?.getParcelable("place") as Place?
        binding.title.text = place?.title
        binding.address.text = place?.address
        binding.phone.text = place?.phone
    }
}