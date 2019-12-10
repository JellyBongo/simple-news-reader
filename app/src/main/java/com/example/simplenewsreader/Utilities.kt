package com.example.simplenewsreader

import android.content.Context
import androidx.preference.ListPreference
import com.example.simplenewsreader.activities.MainActivity
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

    /**
     * Changes the app's locale according to the preference's value
     */
    fun changeLanguage(context: Context?, languagePreference: String?): Boolean {
        if(languagePreference == "Russian")
            setLanguage(context,"ru")
        else
            setLanguage(context, "en")
        return true
    }

    private fun setLanguage(context: Context?, language: String){
        val locale = Locale(language)
        val res = context?.resources
        val dm = res?.displayMetrics
        val configuration = res?.configuration
        configuration?.setLocale(locale)
        res?.updateConfiguration(configuration, dm)
    }
}