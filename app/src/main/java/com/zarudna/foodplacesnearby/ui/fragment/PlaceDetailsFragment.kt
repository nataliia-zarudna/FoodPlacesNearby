package com.zarudna.foodplacesnearby.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zarudna.foodplacesnearby.R
import com.zarudna.foodplacesnearby.databinding.FragmentPlaceDetailsBinding
import com.zarudna.foodplacesnearby.model.entiry.Place

class PlaceDetailsFragment() : DialogFragment() {

    private val binding by lazy {
        FragmentPlaceDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireActivity())
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
        binding.tvTitle.text = place?.title
        binding.tvAddress.text = place?.address

        if (place?.phone?.isNotEmpty() == true) {
            binding.tvPhone.text = place.phone
            binding.tvPhone.visibility = View.VISIBLE
            binding.ivPhoneIcon.visibility = View.VISIBLE
        }
    }
}