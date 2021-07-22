package com.zarudna.foodplacesnearby.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zarudna.foodplacesnearby.ui.fragment.ListFragment
import com.zarudna.foodplacesnearby.ui.fragment.MapFragment

class PlaceTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MapFragment()
            1 -> ListFragment()
            else -> throw IllegalArgumentException("Wrong place fragment position $position")
        }
    }
}