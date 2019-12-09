package com.example.simplenewsreader

import java.text.SimpleDateFormat
import java.util.*

object Utilities {
    /**
     * Parses given string with date to java's Date object
     * The format is the one used in rss feeds
     */
    fun parseDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US)
        return dateFormat.parse(dateString)
    }

    /**
     * Converts given date to "HH:mm:ss, d MMM yyyy" format
     */
    fun prettifyDate(date: Date): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss, d MMM yyyy", Locale.US)
        return dateFormat.format(date)
    }
}