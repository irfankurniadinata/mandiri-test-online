package com.irfan.mandiriTestOption2.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private const val raw_date = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val format_ddMMMyyyy = "dd MMM yyyy"

    fun formatSimple(input: String): String {
        val from = SimpleDateFormat(raw_date, Locale("id", "ID"))
        val to = SimpleDateFormat(format_ddMMMyyyy, Locale("id", "ID"))
        var date: Date? = null
        try {
            date = from.parse(input)
            return to.format(date ?: "")
        } catch (e: ParseException) {
            e.printStackTrace()
            return input
        }
    }
}