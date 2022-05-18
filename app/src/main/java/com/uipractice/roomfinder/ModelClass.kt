package com.uipractice.roomfinder

import androidx.annotation.DrawableRes

data class PropertyDescription(
    val propertyName: String,
    val propertyPrice: String,
    val userProfile: String,
    val userName: String,
    val userLocation: String,
    val userContact: Long,
    val userEmail: String,
    val propertyAddress: String,
    val propertyArea: String,
    val propertyApplied: Int,
    val propertyView: Int,
    val isAvailable: String,
    val propertyOwned: String,
    val imageList: MutableList<String>,
    val description: String,
    val facilities: MutableList<String>
)

val propertyDescription = PropertyDescription(
    "1 Big Hall at Lalitpur",
    "8000",
    "NULL",
    "Courtney Henry",
    "Landlord",
    9885452545,
    "abc@gmail.com",
    "1.2 km from Gwarko",
    "Mahalaxmi, Lalitpur",
    0,
    19,
    "Available",
    "Property Owned By: Alok",
    mutableListOf("NULL", "NULL", "NULL", "NULL", "NULL"),
    "1 big hall room for rent at lalitpur, ktm with the facilities of bike parking and tap water . it offers 1 bedroom,and a 1 common bathroom for whole flat.  It is suitable for student only. Price is negotiable for student only. ",
    mutableListOf("1 Big Hall", "Shared Toilet", "Bikes and Car Parking", "24/7 Water facility")
)

data class CitiesAndProperties(
    val cityName: String,
    val totalProperties: Int
)

val citiesAndProperties = listOf(
    CitiesAndProperties("LalitpurLalitpurLalitpurLalitpurLalitpurLalitpurLalitpurLalitpurLalitpur", 10),
    CitiesAndProperties("Lainchaur", 10),
    CitiesAndProperties("Lalitpur", 10),
    CitiesAndProperties("Lainchaur", 10),
    CitiesAndProperties("Lalitpur", 10),
    CitiesAndProperties("Lainchaur", 10),
    CitiesAndProperties("Lalitpur", 10),
    CitiesAndProperties("Lainchaur", 10)
)

data class SettingsModelClass(
    @DrawableRes val icon: Int,
    val settingName: String,
    val settingDescription: String,
    var isExpanded: Boolean = false
)

val settingsModelClass = mutableListOf(
    SettingsModelClass(R.drawable.ic_edit_profile, "Edit Profile", "Edit all the basic profile information associated with your profile"),
    SettingsModelClass(R.drawable.ic_notification, "Notifications", "Edit all the basic profile information associated with your profile"),
    SettingsModelClass(R.drawable.ic_recent_viewed, "Recent Viewed", "Edit all the basic profile information associated with your profile"),
    SettingsModelClass(R.drawable.ic_get_help, "Get Help", "Edit all the basic profile information associated with your profile"),
    SettingsModelClass(R.drawable.ic_about_us, "About us", "Edit all the basic profile information associated with your profile"),
    SettingsModelClass(R.drawable.ic_logout, "Sign Out", "Edit all the basic profile information associated with your profile")
)