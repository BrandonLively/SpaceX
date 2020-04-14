package com.wkdrabbit.space_x.ui.models

import com.google.gson.annotations.SerializedName
import com.wkdrabbit.space_x.formatEpochDateTime
import java.text.SimpleDateFormat
import java.util.*

data class Launch(
    @SerializedName("flight_number")    val flightNumber: Long,
    @SerializedName("mission_name")     val missionName: String,
    @SerializedName("rocket")           val rocket: Rocket,
    @SerializedName("launch_site")      val launchSite: LaunchSite,
    @SerializedName("launch_date_unix") val launchDate: Long,
    @SerializedName("links")            val images: Images
){
    override fun toString(): String {
        return "Flight Number - $flightNumber\n" +
                "Mission Name - $missionName\n" +
                "Rocket Name - ${rocket.name}\n" +
                "Launch Site - ${launchSite.name}\n" +
                "Launch Date - ${formatEpochDateTime(launchDate)}\n"
    }
}

data class Rocket(
    @SerializedName("rocket_name") val name: String
)

data class LaunchSite(
    @SerializedName("site_name") val name: String
)

data class Images(
    @SerializedName("mission_patch") val imageUrl: String
)