package com.zarudna.foodplacesnearby.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zarudna.foodplacesnearby.R
import com.zarudna.foodplacesnearby.model.entiry.Place

class PlacesAdapter(val onPlaceClickListener: OnPlaceClickListener) :
    RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    var places: List<Place> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.title)

        fun bind(place: Place) {
            title.text = place.title

            itemView.setOnClickListener {
                onPlaceClickListener.onPlaceClick(place)
            }
        }
    }

    interface OnPlaceClickListener {
        fun onPlaceClick(place: Place)
    }
}