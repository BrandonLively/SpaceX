package com.wkdrabbit.space_x.ui.models

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("flight_number") val flightNumber: Long,
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("rocket") val rocket: Rocket,
    @SerializedName("launch_site") val launchSite: LaunchSite,
    @SerializedName("launch_date_unix") val launchDate: Long,
    @SerializedName("links") val patchImageUrl: Images
)

data class Rocket(
    @SerializedName("rocket_name") val name: String
)

data class LaunchSite(
    @SerializedName("site_name") val name: String
)

data class Images(
    @SerializedName("mission_patch") val imageUrl: String
)