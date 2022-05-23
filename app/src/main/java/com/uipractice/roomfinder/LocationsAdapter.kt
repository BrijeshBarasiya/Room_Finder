package com.uipractice.roomfinder

class LocationsAdapter(
    private val elements: List<CitiesAndProperties>,
    count: Int = elements.size
): BaseAdapter<CitiesAndProperties>(elements, count) {

    override fun getItemViewType(position: Int): Int = R.layout.recycler_locations

}