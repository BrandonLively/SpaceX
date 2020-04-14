package com.wkdrabbit.space_x

import java.text.SimpleDateFormat
import java.util.*

fun formatEpochDateTime(epochTime: Long):String{
    val date = Date(epochTime * 1000L)
    val format = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"))
    return format.format(date)
}