package com.wkdrabbit.space_x.ui.models

import com.google.gson.annotations.SerializedName
import com.wkdrabbit.space_x.formatEpochDateTime

data class Launch(
    @SerializedName("flight_number")          val flightNumber: Long = 0,
    @SerializedName("mission_name")           val missionName: String = "",
    @SerializedName("rocket")                 val rocket: Rocket = Rocket(),
    @SerializedName("launch_site")            val launchSite: LaunchSite = LaunchSite(),
    @SerializedName("launch_date_unix")       val launchDate: Long = 0,
    @SerializedName("launch_success")         val launchSuccess: Boolean = false,
    @SerializedName("ships")                  val ships: List<String> = listOf(""),
    @SerializedName("telemetry")              val telemetry: Telemetry = Telemetry(),
    @SerializedName("launch_failure_details") val launchFailureDetails: LaunchFailureDetails = LaunchFailureDetails(),
    @SerializedName("links")                  val links: Links = Links(),
    @SerializedName("details")                var details: String = ""
) {
    //overrides toString method to return formatted launch details
    override fun toString(): String {
        var launchFailureStats = ""
        var launchStatus = ""

        if (launchSuccess) {
            launchStatus = "Success"
        } else {
            launchStatus = "Failure"
            val reason = if(launchFailureDetails.reason == "") "Unknown" else launchFailureDetails.reason
            val altitude = if(launchFailureDetails.altitude == "") "Unknown" else launchFailureDetails.altitude
            val time = if(launchFailureDetails.time == 0L) "Unknown" else formatEpochDateTime(launchFailureDetails.time)
            launchFailureStats = "\nFailure Report \n"+
                    "Reason - $reason\n" +
                    "Altitude - $altitude\n" +
                    "Time - $time\n"
        }

        details = if(details == "null" || details == "" || details == null) "" else "\nDETAILS\n$details"

        return "GENERAL\n" +
                "Flight Number - $flightNumber\n" +
                "Mission Name - $missionName\n" +
                "Rocket Name - ${rocket.name}\n" +
                "Nationality - ${rocket.secondStage.payloads[0].nationality}\n" +
                "\nLAUNCH\n" +
                "Launch Site - ${launchSite.name}\n" +
                "Launch Date - ${formatEpochDateTime(launchDate)}\n" +
                "Launch Status - $launchStatus\n" +
                launchFailureStats +
                "\nPAYLOAD\n" +
                "Payload Type - ${rocket.secondStage.payloads[0].payloadType}\n" +
                "Payload Mass - ${rocket.secondStage.payloads[0].payloadMass} KG\n" +
                 details
    }
}

data class Rocket(
    @SerializedName("rocket_name")  val name: String = "",
    @SerializedName("second_stage") val secondStage: SecondStage = SecondStage()
)

data class SecondStage(
    @SerializedName("payloads") val payloads: List<Payload> = listOf(Payload())
)

data class Telemetry(
    @SerializedName("flight_club") val flight_club: String = ""
)

data class LaunchFailureDetails(
    @SerializedName("time")     val time: Long = 0L,
    @SerializedName("altitude") val altitude: String = "",
    @SerializedName("reason")   val reason: String = ""
)

data class LaunchSite(
    @SerializedName("site_name") val name: String = ""
)

data class Links(
    @SerializedName("mission_patch")       val imageUrl: String = "",
    @SerializedName("mission_patch_small") val imageUrlSmall: String = ""
)

data class Payload(
    @SerializedName("payload_mass_kg") val payloadMass: Float = 0f,
    @SerializedName("payload_type")    val payloadType: String = "",
    @SerializedName("nationality")     val nationality: String = ""
)