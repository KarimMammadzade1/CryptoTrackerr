package com.aqsin.helpers.adapters

import java.text.SimpleDateFormat
import java.util.*

object UtilityHelper {
    val formatFulldate = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")

    fun getTodayString() : String {
        val currentCalendar: Calendar = Calendar.getInstance()
        return formatFulldate.format(currentCalendar.time)
    }
}